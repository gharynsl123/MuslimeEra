package com.appteam.muslimeera.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.appteam.muslimeera.databinding.ActivityMainBinding
import com.appteam.muslimeera.ui.BacaHadits
import com.appteam.muslimeera.ui.BacaQuran
import com.appteam.muslimeera.ui.ProfileActivity
import com.appteam.muslimeera.ui.notes.NotesPage

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private var _viewModel: MainViewModel? = null
    private val viewModel get() = _viewModel as MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val mLayoutManager = CenterItemLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val mAdapter = MotivationAdapter()

        _viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.apply {
            getDataMotive()
            motiveResponse.observe(this@MainActivity) {
                binding.rvMotivation.apply {
                    adapter = mAdapter
                    layoutManager = mLayoutManager
                    mAdapter.setMotivation(it)
                    PagerSnapHelper().attachToRecyclerView(this)
                }
            }
            isLoading.observe(this@MainActivity) { shimmerLayout(it) }
            isError.observe(this@MainActivity) { showError(it) }
        }

        binding.apply {
            btnNotes.setOnClickListener {
                val intent = Intent(this@MainActivity, NotesPage::class.java)
                startActivity(intent)
                finish()
            }
            btnBacaQuran.setOnClickListener {
                val intent = Intent(this@MainActivity, BacaQuran::class.java)
                startActivity(intent)
            }
            btnBacaHadits.setOnClickListener {
                val intent = Intent(this@MainActivity, BacaHadits::class.java)
                startActivity(intent)
            }
            photoProfile.setOnClickListener {
                startActivity(Intent(this@MainActivity, ProfileActivity::class.java))
            }
        }
    }

    private fun shimmerLayout(isLoading: Boolean?) {
        binding.apply {
            if (isLoading == true) {
                shimmerViewContainer.startShimmer()
                shimmerViewContainer.visibility = View.VISIBLE
                rvMotivation.visibility = View.GONE
            } else {
                shimmerViewContainer.stopShimmer()
                shimmerViewContainer.visibility = View.GONE
                rvMotivation.visibility = View.VISIBLE
            }
        }
    }

    private fun showError(it: Throwable?) {
        Log.e("MainActivity", "showError: $it")
    }

}
