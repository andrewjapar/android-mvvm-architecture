package com.andrewjapar.android_mvvm_architecture.utils

import android.content.Context
import android.graphics.drawable.Drawable
import com.andrewjapar.android_mvvm_architecture.R
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

object ImageViewUtil {

    fun loadImage(
        context: Context?,
        sourceUrl: String,
        rounded: Boolean = false
    ): RequestBuilder<Drawable>? {

        return if (rounded) {
            Glide.with(context!!).load(sourceUrl)
                .apply(
                    RequestOptions().transforms(
                        CenterCrop(),
                        RoundedCorners(100)
                    ).placeholder(R.color.placeholder_background)
                )
        } else Glide.with(context!!).load(sourceUrl).apply(RequestOptions().placeholder(R.color.placeholder_background))
    }
}