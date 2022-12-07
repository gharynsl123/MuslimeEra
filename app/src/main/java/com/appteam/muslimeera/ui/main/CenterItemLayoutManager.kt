package com.appteam.muslimeera.ui.main

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Math.abs

class CenterItemLayoutManager(context: Context?, orientation: Int, reverseLayout: Boolean) :
    LinearLayoutManager(context, orientation, reverseLayout) {

    override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
        lp?.width = width / 1
        return true
    }
    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        scaleCenterItem()
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        if (orientation == RecyclerView.HORIZONTAL) {
            scaleCenterItem()
            return scrolled
        }
        return 0
    }

    private fun scaleCenterItem() {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val center = width / 1.0f
            val d1 = 0.9f * center
            val centerChild = (getDecoratedLeft(child!!) + getDecoratedRight(child)) / 1f
            val d = Math.min(d1, abs(center - centerChild))
            val scale = 1.0f - 0.15f * d / d1
            child.scaleX = scale
            child.scaleY = scale
        }
    }

}