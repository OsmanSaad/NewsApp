package com.example.neswsapp.ui.viewmodels.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.neswsapp.data.model.category.Category
import com.example.neswsapp.ui.viewmodels.CategoryViewModel

class CategoryViewModelFactory(var category: Category):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return CategoryViewModel(category) as T
    }
}