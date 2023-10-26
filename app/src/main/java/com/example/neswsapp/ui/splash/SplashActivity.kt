package com.example.neswsapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.neswsapp.R
import com.example.neswsapp.ui.activity.CategoryActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setupSplashScreen()
    }

    private fun setupSplashScreen(){
        Handler(Looper.getMainLooper()).postDelayed({
            var intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
            finish()
        },1500)
    }
}