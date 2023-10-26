package com.example.neswsapp.ui.application

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.neswsapp.data.constants.Constants
import java.util.Locale

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences(Constants.PREFERENCES_NAME, MODE_PRIVATE)
        val language = sharedPreferences.getString(Constants.PREFERENCES_KEY, "")
        val locale = LocaleListCompat.create(Locale(language))
        AppCompatDelegate.setApplicationLocales(locale)
    }
}