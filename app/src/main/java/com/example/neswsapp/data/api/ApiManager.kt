package com.example.neswsapp.data.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    companion object{
        @Volatile
        private var INSTANCE:Retrofit? = null
        private fun getInstance():Retrofit{
             var currentInstance = INSTANCE
            if(currentInstance!=null){
                return currentInstance
            }
            synchronized(this){
                var instance = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()
                INSTANCE = instance
                return instance
            }
        }
        fun getApi():ApiServices{
            return getInstance().create(ApiServices::class.java)
        }

    }
}