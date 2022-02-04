package com.example.imagesearchpaging.effect

import android.graphics.Color
import com.facebook.shimmer.Shimmer.ColorHighlightBuilder
import com.facebook.shimmer.ShimmerDrawable


class Shimmer {
    companion object {
        fun getEffect(): ShimmerDrawable {
            // Initialize shimmer
            val shimmer = ColorHighlightBuilder()
                .setBaseColor(Color.parseColor("#AEADAD"))
                .setBaseAlpha(1F)
                .setHighlightColor(Color.parseColor("#E7E7E7"))
                .setHighlightAlpha(1F)
                .setDropoff(50F)
                .build()
            // Initialize shimmer drawable
            val shimmerDrawable = ShimmerDrawable()
            // Set shimmer
            shimmerDrawable.setShimmer(shimmer)
            return shimmerDrawable
        }
    }
}