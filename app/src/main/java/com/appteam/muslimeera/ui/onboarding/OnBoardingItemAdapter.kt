package com.appteam.muslimeera

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appteam.muslimeera.databinding.ItemCountainerOnboardingBinding
import com.bumptech.glide.Glide

class OnBoardingItemAdapter(private val onBoardingItems : List<OnBoardingItem>): RecyclerView.Adapter<OnBoardingItemAdapter.HolderBoarding>() {



    inner class HolderBoarding (val binding: ItemCountainerOnboardingBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= HolderBoarding(
        ItemCountainerOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: HolderBoarding, position: Int) {
        val onBoards = onBoardingItems[position]
        holder.binding.apply {
            tvTitle.text = onBoards.titleOnBoarding
            tvDesc.text = onBoards.descriptionOnBoarding

            Glide.with(ivOnBoarding).load(onBoards.onBoardingImages).into(ivOnBoarding)
        }

    }

    override fun getItemCount(): Int = onBoardingItems.size

}