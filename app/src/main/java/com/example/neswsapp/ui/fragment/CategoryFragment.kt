package com.example.neswsapp.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.neswsapp.ui.listner.OnItemClickListner
import com.example.neswsapp.data.model.category.Category
import com.example.neswsapp.databinding.FragmentCategoryBinding
import com.example.neswsapp.ui.activity.CategoryActivity
import com.example.neswsapp.ui.listner.OnNavigateNewsFragmentListner
import com.example.neswsapp.ui.viewmodels.CategoryViewModel
import com.example.neswsapp.ui.viewmodels.viewmodelfactory.CategoryViewModelFactory


class CategoryFragment : Fragment() {
    lateinit var binding: FragmentCategoryBinding
    lateinit var categoryViewModels:CategoryViewModel
    var onNavigateNewsFragmentListner: OnNavigateNewsFragmentListner?=null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context?.let {
            onNavigateNewsFragmentListner = (context as CategoryActivity)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater,container,false)
        var viewmodelFactory = CategoryViewModelFactory(Category())
        categoryViewModels = ViewModelProvider(this,viewmodelFactory).get(CategoryViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var adabter = CategoryAdabter(categoryViewModels.getCategoryList())
        binding.categoryList.adapter = adabter
        adabter.onItemClickListner = object : OnItemClickListner {
            override fun onItemClick(category: String) {
                if(onNavigateNewsFragmentListner!=null){
                    onNavigateNewsFragmentListner?.onNavigate(category)
                }
//                var intent = Intent(requireContext(), NewsActivity::class.java)
//                intent.putExtra(Constants.CATEGORY_KEY,category)
//                startActivity(intent)
            }
        }
    }



}