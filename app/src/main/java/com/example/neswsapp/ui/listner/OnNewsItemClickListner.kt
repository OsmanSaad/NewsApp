package com.example.neswsapp.ui.listner

import com.example.neswsapp.data.model.convertermodels.NewsAdabterData

interface OnNewsItemClickListner {
    fun onNewsItemClick(position:Int,item: NewsAdabterData)
}