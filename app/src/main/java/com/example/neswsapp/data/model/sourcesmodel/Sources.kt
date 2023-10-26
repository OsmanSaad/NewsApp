package com.example.neswsapp.data.model.sourcesmodel

import com.google.gson.annotations.SerializedName

data class Sources(

    @field:SerializedName("sources")
	val sources: List<SourcesItem?>? = null,

    @field:SerializedName("status")
	val status: String? = null
)