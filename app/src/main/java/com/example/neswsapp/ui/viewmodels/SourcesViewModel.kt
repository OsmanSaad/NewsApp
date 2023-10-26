package com.example.neswsapp.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neswsapp.data.model.convertermodels.NewsAdabterData
import com.example.neswsapp.data.model.newsmodel.ArticlesItem
import com.example.neswsapp.data.model.sourcesmodel.SourcesItem
import com.example.neswsapp.data.repository.Repository
import com.example.neswsapp.utils.BaseViews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

class SourcesViewModel : ViewModel() {

    var repository = Repository()

    var loading = MutableLiveData<Boolean>()
    var noInternetImage = MutableLiveData<Boolean>()

    private var _sources = MutableLiveData<List<SourcesItem?>?>()
    var sources: LiveData<List<SourcesItem?>?> = _sources

    private var _news = MutableLiveData<List<NewsAdabterData>?>(emptyList())
    var news: LiveData<List<NewsAdabterData>?> = _news

    private var _newsSearch = MutableLiveData<List<NewsAdabterData>?>()
    var newsSearch: LiveData<List<NewsAdabterData>?> = _newsSearch

    var newsSource = MutableLiveData<SourcesItem>()
    var currentNewsIsEmpty = MutableLiveData<Boolean>()
    var currentSourcesIsEmpty = MutableLiveData<Boolean>()

    fun getSourceLiveData(category: String?, context: Context) {
        _sources.postValue(emptyList())
        loading.postValue(true)
        category?.let {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getFlowSources(category)
                    .catch {
                        withContext(Dispatchers.Main) {
                            BaseViews.showDialogue(context, {
                                if(currentSourcesIsEmpty.value==true)
                                    loading.postValue(true)
                                noInternetImage.postValue(false)
                                getSourceLiveData(category, context)
                            }, {
                                Log.d("count",currentSourcesIsEmpty.value.toString())
                                loading.postValue(false)
                                if (currentSourcesIsEmpty.value == true) {
                                    noInternetImage.postValue(true)
                                }
                            }, {
                                loading.postValue(false)
                                Log.d("count",currentSourcesIsEmpty.value.toString())
                                if (currentSourcesIsEmpty.value == true) {
                                    noInternetImage.postValue(true)
                                }
                            })
                        }
                    }.collect { source ->
                        noInternetImage.postValue(false)
                        loading.postValue(false)
                        _sources.postValue(source.sources)
                    }
            }
        }
    }

    fun getNewsLiveData(context: Context) {
        _news.postValue(emptyList())
        loading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            newsSource.value?.id?.let { sourceid ->
                repository.getFlowNews(sourceid).catch {
                    withContext(Dispatchers.Main) {
                        BaseViews.showDialogue(context, {
                            if(currentNewsIsEmpty.value==true)
                                loading.postValue(true)
                            noInternetImage.postValue(false)
                            getNewsLiveData(context)
                        }, {
                            if (currentNewsIsEmpty.value==true) {
                                noInternetImage.postValue(true)
                            }
                            loading.postValue(false)

                        }, {
                            Log.d("count",currentSourcesIsEmpty.value.toString())
                            loading.postValue(false)
                            if (currentNewsIsEmpty.value == true) {
                                noInternetImage.postValue(true)
                            }
                        })
                    }
                }.collect { sourceItems ->
                    noInternetImage.postValue(false)
                    loading.postValue(false)
                    _news.postValue(converttoNewsAdapterData(sourceItems))
                }
            }
        }
    }

    private fun converttoNewsAdapterData(data: List<ArticlesItem?>?): List<NewsAdabterData> {
        var newsAdabterData = mutableListOf<NewsAdabterData>()
        data?.forEach {
            newsAdabterData.add(
                NewsAdabterData(
                    source = newsSource.value?.name,
                    description = it?.description,
                    time = dateFormate(requireNotNull(it?.publishedAt)),
                    image = it?.urlToImage,
                    title = it?.title,
                    url = it?.url
                )
            )
        }
        return newsAdabterData.toList()
    }

    private fun dateFormate(stringDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val date = inputFormat.parse(stringDate)
        val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val formattedDate = outputFormat.format(date)
        return formattedDate
    }

    fun newsSearch(text: String) {
        var resultofSearch = mutableListOf<NewsAdabterData>()
        _news?.let { newsList ->
            newsList.value?.forEach {
                if (it.title?.contains(text)!!)
                    resultofSearch.add(it)
            }
        }
        _newsSearch.postValue(resultofSearch)
    }
}