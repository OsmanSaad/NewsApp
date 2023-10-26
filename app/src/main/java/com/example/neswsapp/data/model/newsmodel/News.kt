package com.example.neswsapp.data.model.newsmodel

import com.google.gson.annotations.SerializedName

data class News(

    @field:SerializedName("totalResults")
	val totalResults: Int? = null,

    @field:SerializedName("articles")
	val articles: List<ArticlesItem?>? = null,

    @field:SerializedName("status")
	val status: String? = null
)