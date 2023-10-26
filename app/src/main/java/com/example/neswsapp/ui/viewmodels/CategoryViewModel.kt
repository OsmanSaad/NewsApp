package com.example.neswsapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.neswsapp.data.model.category.Category

class CategoryViewModel(var data: Category):ViewModel() {
    fun getCategoryList():List<Category>{
        return data.getCategoryDate()
    }
}