package com.example.neswsapp.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.neswsapp.data.constants.Constants
import com.example.neswsapp.data.model.convertermodels.NewsAdabterData
import com.example.neswsapp.databinding.ActivityDetailsNewsBinding

class DetailsNewsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var newsDetails = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constants.NEWS_DETAILS_KEY, NewsAdabterData::class.java)
        } else {
            intent.getParcelableExtra<NewsAdabterData>(Constants.NEWS_DETAILS_KEY)
        }
        newsDetails?.let {
            initNewsDetailsData(newsDetails)
        }

    }

    private fun initNewsDetailsData(newsDetails: NewsAdabterData){
        binding.dateNewsDetails.text = newsDetails.time
        binding.titleNewsDetails.text = newsDetails.title
        binding.descriptionNewsDetails.text = newsDetails.description
        binding.gotowebNewsDetails.setOnClickListener {
            var intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse(newsDetails.url)
            startActivity(intent)
        }
        binding.sourcenameNewsDetails.text = newsDetails.source
        Glide.with(binding.newsDetailsImage)
            .load(newsDetails.image)
            .into(binding.newsDetailsImage)
    }
}