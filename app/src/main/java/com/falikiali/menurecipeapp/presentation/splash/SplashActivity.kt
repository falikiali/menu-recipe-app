package com.falikiali.menurecipeapp.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.falikiali.menurecipeapp.R
import com.falikiali.menurecipeapp.databinding.ActivitySplashBinding
import com.falikiali.menurecipeapp.presentation.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private val binding: ActivitySplashBinding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        startIntent()
    }

    private fun startIntent() {
        Handler(mainLooper).postDelayed({
            val i = Intent(this@SplashActivity, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(i)
        }, 3500)
    }

}