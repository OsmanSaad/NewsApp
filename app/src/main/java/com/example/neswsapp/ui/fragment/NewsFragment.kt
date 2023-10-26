package com.example.neswsapp.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.example.neswsapp.R
import com.example.neswsapp.data.constants.Constants
import com.example.neswsapp.data.model.convertermodels.NewsAdabterData
import com.example.neswsapp.data.model.sourcesmodel.SourcesItem
import com.example.neswsapp.databinding.FragmentNewsBinding
import com.example.neswsapp.ui.activity.CategoryActivity
import com.example.neswsapp.ui.activity.DetailsNewsActivity
import com.example.neswsapp.ui.adapter.NewsAdabter
import com.example.neswsapp.ui.listner.OnNavigateNewsFragmentListner
import com.example.neswsapp.ui.listner.OnNewsItemClickListner
import com.example.neswsapp.ui.viewmodels.SourcesViewModel
import com.google.android.material.tabs.TabLayout

class NewsFragment : Fragment() {
    lateinit var binding: FragmentNewsBinding
    lateinit var sourceViewModel: SourcesViewModel
    lateinit var adapter: NewsAdabter
    private var category: String? = null
    var newsSearchRef:SearchView?=null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context?.let { context ->
          var mainActivity = (context as CategoryActivity)
          newsSearchRef = mainActivity.binding.newssearch
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getString(Constants.CATEGORY_KEY)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater,container,false)
        adapter = NewsAdabter(emptyList())
        binding.newsList.adapter = adapter
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        sourceViewModel = ViewModelProvider(this).get(SourcesViewModel::class.java)
        sourceViewModel.getSourceLiveData(category,requireContext())
        sourceViewModel.sources.observe(viewLifecycleOwner){ sources->
             sources?.let { setupTabs(sources) } }
        binding.viewmodel = sourceViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        Log.d("cou",adapter.itemCount.toString())

        tabSelection()
        newsSearch()
        if(adapter.itemCount==0){
            sourceViewModel.currentSourcesIsEmpty.postValue(true)
            sourceViewModel.currentNewsIsEmpty.postValue(true)
        }else{
            sourceViewModel.currentSourcesIsEmpty.postValue(false)
            sourceViewModel.currentNewsIsEmpty.postValue(false)
        }

    }
    private fun setupTabs(sources:List<SourcesItem?>){
        sources.forEach {source->
            val tab = binding.tabLayout.newTab()
            tab.text = source?.name
            tab.tag = source
            binding.tabLayout.addTab(tab)
            var params = LinearLayout.LayoutParams(tab.view.layoutParams)
            params.marginStart = 10
            params.marginEnd = 10
            tab.view.layoutParams = params
        }
    }
    private fun tabSelection(){
        binding.tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.tag?.let { sourceItem->
                    sourceViewModel.newsSource.value = (sourceItem as SourcesItem)
                    sourceViewModel.getNewsLiveData(requireContext())
                    getNews()
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
    private fun getNews(){
        sourceViewModel.news.observe(viewLifecycleOwner){newsAdapterData->
            adapter.setnews(newsAdapterData)
            adapter.onNewsItemClickListner = object : OnNewsItemClickListner {
                override fun onNewsItemClick(position: Int, item: NewsAdabterData) {
                    var intent = Intent(requireContext(), DetailsNewsActivity::class.java)
                    intent.putExtra(Constants.NEWS_DETAILS_KEY,item)
                    startActivity(intent)
                }

            }
        }
    }
    private fun newsSearch(){
        newsSearchRef?.let {newsSearch->
            newsSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if(query!=null){
                        sourceViewModel.newsSearch(query)
                        sourceViewModel.newsSearch.observe(viewLifecycleOwner){
                            adapter.setnews(it)
                        }
                    }
                    else{ getNews() }
                    newsSearch.isIconified = true
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(!newText.isNullOrBlank()){
                        sourceViewModel.newsSearch(newText)
                        sourceViewModel.newsSearch.observe(viewLifecycleOwner){
                            adapter.setnews(it)
                        }
                    }
                    else{ getNews() }
                    return true
                }

            })

            newsSearch.setOnCloseListener {
                getNews()
                false
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(category: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.CATEGORY_KEY, category)
                }
            }
    }
}