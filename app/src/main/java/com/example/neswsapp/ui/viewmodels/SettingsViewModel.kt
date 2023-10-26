package com.example.neswsapp.ui.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.neswsapp.data.constants.Constants
import java.util.Locale


class SettingsViewModel(application:Application):AndroidViewModel(application) {
    var sharedPreferences = application.getSharedPreferences(Constants.PREFERENCES_NAME,Context.MODE_PRIVATE)
    var editor = sharedPreferences.edit()
    fun saveLanguage(locale:String){
        editor.putString(Constants.PREFERENCES_KEY,locale).apply()
    }
    fun getLanguage():String{
        return sharedPreferences.getString(Constants.PREFERENCES_KEY,Locale.getDefault().language)?:Locale.getDefault().language
    }
}