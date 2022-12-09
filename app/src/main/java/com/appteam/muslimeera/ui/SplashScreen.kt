package com.appteam.muslimeera.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.appteam.muslimeera.R
import com.appteam.muslimeera.ui.onboarding.OnBoarding

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed({
            val intent = Intent(this, OnBoarding::class.java)
            startActivity(intent) // Pindah ke Home Activity setelah 3 detik
            finish()
        }, 1500)

    }
}