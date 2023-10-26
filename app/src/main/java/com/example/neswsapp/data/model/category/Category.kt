package com.example.neswsapp.data.model.category

import com.example.neswsapp.R

class Category(
    var category:String?=null,
    var title:Int?=null,
    var image:Int? =null,
    var bgColor:Int?=null,
    var shape:Int?=null
){

    fun getCategoryDate():List<Category>{
        var categoryList = listOf(
            Category(category = "sports",
                title = R.string.Sports,
                image = R.drawable.sports,
                bgColor = R.color.sport_category_color,
                shape = R.drawable.category_left_item_bg),
            Category(category = "general",
                title = R.string.General,
                image = R.drawable.politics,
                bgColor = R.color.politices_category_color,
                shape = R.drawable.category_right_item_bg),
            Category(category = "health",
                title = R.string.Health,
                image = R.drawable.health,
                bgColor = R.color.health_category_color,
                shape = R.drawable.category_left_item_bg),
            Category(category = "business",
                title = R.string.Business,
                image = R.drawable.bussines,
                bgColor = R.color.business_category_color,
                shape = R.drawable.category_right_item_bg),
            Category(category = "entertainment",
                title = R.string.Entertainment,
                image = R.drawable.environment,
                bgColor = R.color.entertainment_category_color,
                shape = R.drawable.category_left_item_bg),
            Category(category = "science",
                title = R.string.Science,
                image = R.drawable.science,
                bgColor = R.color.science_category_color,
                shape = R.drawable.category_right_item_bg),
        )
        return categoryList
    }
}
