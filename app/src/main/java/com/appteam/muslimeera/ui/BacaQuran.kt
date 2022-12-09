package com.appteam.muslimeera.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appteam.muslimeera.databinding.ActivityBacaQuranBinding

class BacaQuran : AppCompatActivity() {

    private var _binding : ActivityBacaQuranBinding? = null
    private val binding get() = _binding as ActivityBacaQuranBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBacaQuranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        _binding = null
//    }
}