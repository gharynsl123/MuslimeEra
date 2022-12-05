package com.appteam.muslimeera.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appteam.muslimeera.data.respone.MotivationResponseItem
import com.appteam.muslimeera.ui.BacaHadits
import com.appteam.muslimeera.ui.BacaQuran
import com.appteam.muslimeera.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private var _viewModel: MainViewModel? = null
    private val viewModel get() = _viewModel as MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.apply {
            getDataMotive()
            motiveResponse.observe(this@MainActivity) { setUpListMotivation() }
            isError.observe(this@MainActivity) { showError(it) }
        }

        binding.btnBacaQuran.setOnClickListener {
            val intent = Intent(this, BacaQuran::class.java)
            startActivity(intent)
        }
        binding.btnBacaHadits.setOnClickListener {
            val intent = Intent(this, BacaHadits::class.java)
            startActivity(intent)
        }
    }

    private fun setUpListMotivation() {
        binding.rvMotivation.apply {
            adapter = MotivationAdapter()
            val mLayoutManager = CenterItemLayoutManager(context, RecyclerView.HORIZONTAL, false)
            layoutManager = mLayoutManager
        }
    }

    private fun showError(it: Throwable?) {
        Log.e("MainActivity", "showError: $it")
    }

}