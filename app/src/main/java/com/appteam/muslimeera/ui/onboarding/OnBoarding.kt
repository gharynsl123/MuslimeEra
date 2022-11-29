package com.appteam.muslimeera

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.text.Layout
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.appteam.muslimeera.databinding.ActivityOnBoardingBinding
import java.util.*


class OnBoarding : AppCompatActivity() {
    private var _binding: ActivityOnBoardingBinding? = null
    private val binding get() = _binding as ActivityOnBoardingBinding

    val onBoardingAdapter = OnBoardingItemAdapter(
        listOf(
            OnBoardingItem(
                onBoardingImages = R.drawable.kelebihan,
                titleOnBoarding = "MEMILIKI LEBIH\nDARI 10 FITUR",
                descriptionOnBoarding = "Nikmati seluruh fitur kami secara mudah dan\ntelah teruji oleh para ahli."
            ),
            OnBoardingItem(
                onBoardingImages = R.drawable.gratis,
                titleOnBoarding = "APLIKASI\nGRATIS 100%",
                descriptionOnBoarding = "Aplikasi di buat untuk memudahkan para \nkaum muslim dalam melakukan ibadah."
            )
        )
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnBoardingItems()
        setUpIndicator()
        setUpCurrentIndicator(0)

    }

    private fun setOnBoardingItems() {
        binding.onBoardingViewPage.adapter = onBoardingAdapter
        binding.onBoardingViewPage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setUpCurrentIndicator(position)
            }
        })
        (binding.onBoardingViewPage.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER

        binding.btnNextOnboard.setOnClickListener {
            if (binding.onBoardingViewPage.currentItem + 1 < onBoardingAdapter.itemCount){
                binding.onBoardingViewPage.currentItem += 1
            }else{
                navigateToHomePage()
            }
        }
    }

    private fun navigateToHomePage() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }

    private fun setUpIndicator() {
//        binding.indicatorBoarding
        val indicator = arrayOfNulls<ImageView>(onBoardingAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicator.indices) {
            indicator[i] = ImageView(applicationContext)
            indicator[i]?.let{
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext, R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                binding.indicatorBoarding.addView(it)
            }
        }
    }

    private fun setUpCurrentIndicator(position: Int){
        val childCount = binding.indicatorBoarding.childCount
        for(i in 0 until childCount){
            val imageView = binding.indicatorBoarding.getChildAt(i) as ImageView
            if (i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext, R.drawable.indicator_active_background
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext, R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }
}