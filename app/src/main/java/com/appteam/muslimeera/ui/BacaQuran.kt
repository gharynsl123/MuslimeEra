package com.appteam.muslimeera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appteam.muslimeera.databinding.ActivityBacaQuranBinding

class BacaQuran : AppCompatActivity() {

    private var _binding : ActivityBacaQuranBinding? = null
    private val binding get() = _binding as ActivityBacaQuranBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBacaQuranBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        _binding = null
    }
}