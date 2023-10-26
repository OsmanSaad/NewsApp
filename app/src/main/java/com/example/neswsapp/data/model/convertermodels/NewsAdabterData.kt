package com.example.neswsapp.data.model.convertermodels

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsAdabterData(
    var source:String?=null,
    var description:String?=null,
    var time:String?=null,
    var image:String?=null,
    var title:String?=null,
    var url:String?=null
):Parcelable
