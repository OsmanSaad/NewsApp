package com.example.neswsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neswsapp.ui.listner.OnNewsItemClickListner
import com.example.neswsapp.data.model.convertermodels.NewsAdabterData
import com.example.neswsapp.databinding.NewsitemBinding

class NewsAdabter(var news:List<NewsAdabterData>?):RecyclerView.Adapter<NewsAdabter.ViewHolder>() {
    var onNewsItemClickListner: OnNewsItemClickListner?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = NewsitemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        news?.let {
            holder.binding.news = news!![position]
            holder.binding.newImage.resetLoader()
            holder.binding.newsDescriptionText.resetLoader()
            holder.binding.time.resetLoader()
            if(onNewsItemClickListner!=null){
                holder.binding.root.setOnClickListener {
                    onNewsItemClickListner?.onNewsItemClick(position,news!![position])
                }
            }
        }
    }
    override fun getItemCount() = news?.size ?: 0
    class ViewHolder(var binding: NewsitemBinding):RecyclerView.ViewHolder(binding.root){

    }
    fun setnews(newNews:List<NewsAdabterData>?){
       newNews?.let {
           this.news = newNews
           notifyDataSetChanged()
       }
    }
}