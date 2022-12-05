package com.appteam.muslimeera.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appteam.muslimeera.data.respone.MotivationResponseItem
import com.appteam.muslimeera.databinding.RowItemMotivationBinding
import com.bumptech.glide.Glide

class MotivationAdapter: RecyclerView.Adapter<MotivationAdapter.MyMotiveHolder>() {

    private val listMotivation = ArrayList<MotivationResponseItem>()

    inner class MyMotiveHolder(val binding: RowItemMotivationBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= MyMotiveHolder(
        RowItemMotivationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyMotiveHolder, po: Int) {
        val data = listMotivation[po]
        holder.binding.apply {
            tvMotivation.text = data.motavation
            Glide.with(backgroundMotivation).load(data.imageBackground).into(backgroundMotivation)
        }
    }

    override fun getItemCount(): Int = listMotivation.size
}