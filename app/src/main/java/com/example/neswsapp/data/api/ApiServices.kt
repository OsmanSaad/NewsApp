package com.example.neswsapp.data.api


import com.example.neswsapp.data.model.newsmodel.News
import com.example.neswsapp.data.model.sourcesmodel.Sources
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiServices {
    @GET("v2/top-headlines/sources")
    suspend fun getSources(
        @Query("category")category:String,
        @Query("apiKey")apiKey:String="4ec85d00f87a44c9a86a52a0912712e3"
    ): Sources

    @GET("v2/everything")
    suspend fun getNews(
        @Query("sources")sources:String,
        @Query("apiKey")apiKey:String="4ec85d00f87a44c9a86a52a0912712e3"
    ):News
}

//https://newsapi.org/v2/everything?sources=abc-news&apiKey=8bbc261df1554b5aa8fa076c47c766b8


//https://newsapi.org/v2/top-headlines/sources?apiKey=8bbc261df1554b5aa8fa076c47c766b8