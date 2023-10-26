package com.example.neswsapp.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neswsapp.ui.listner.OnItemClickListner
import com.example.neswsapp.data.model.category.Category
import com.example.neswsapp.databinding.CategoryitemBinding

class CategoryAdabter(var categoryData: List<Category>):RecyclerView.Adapter<CategoryAdabter.viewHolder>() {

    var onItemClickListner: OnItemClickListner?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var binding = CategoryitemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(binding)
    }
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        var category = categoryData[position]
        holder.binding.category = category
        holder.binding.root.setOnClickListener {
            if(onItemClickListner!=null){
                onItemClickListner?.onItemClick(category.category?:"")
            }
        }
    }
    override fun getItemCount() = categoryData.size
    class viewHolder(var binding: CategoryitemBinding):RecyclerView.ViewHolder(binding.root)
}