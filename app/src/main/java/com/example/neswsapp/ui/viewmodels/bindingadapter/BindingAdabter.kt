package com.example.neswsapp.ui.viewmodels.bindingadapter


import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Locale

@BindingAdapter("app:image")
fun setImage(imageView:ImageView,image:Int){
    imageView.setImageResource(image)
}

@BindingAdapter("app:backgroundcolor")
fun setcolor(view:ViewGroup,colorResource: Int){
    val color = ContextCompat.getColor(view.context, colorResource)
    view.setBackgroundColor(color)
}

@BindingAdapter("app:loadImage")
fun loadNewsImage(imageView: ImageView,url:String?){
    if(url!=null){
        Glide
            .with(imageView)
            .load(url)
            .into(imageView)
    }
}



