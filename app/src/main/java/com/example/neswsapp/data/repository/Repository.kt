package com.example.neswsapp.data.repository

import com.example.neswsapp.data.api.ApiManager
import com.example.neswsapp.data.model.newsmodel.ArticlesItem
import com.example.neswsapp.data.model.sourcesmodel.Sources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Repository {
    suspend fun getFlowSources(category:String):Flow<Sources>{
        return flow {
            val response = ApiManager.getApi().getSources(category)
            emit(response)
        }
    }
    suspend fun getFlowNews(source:String):Flow<List<ArticlesItem?>>{
        return flow {
            ApiManager.getApi().getNews(source).articles?.let { response->
                emit(response)
            }
        }
    }
}