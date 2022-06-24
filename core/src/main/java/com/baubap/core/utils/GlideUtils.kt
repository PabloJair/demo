package com.baubap.core.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.baubap.core.R
import com.bumptech.glide.Glide

object GlideUtils {

  fun loadImage(context: Context, url: String, imageView: ImageView) {

    if (url.isBlank()) {
      Glide.with(context)
          .load(R.drawable.pokeball)
          .placeholder(createProgressBar(context))
          .error(R.drawable.pokeball)
          .into(imageView)
      return
    }
    Glide.with(context)
        .load(url)
        .placeholder(createProgressBar(context))
        .error(R.drawable.pokeball)
        .into(imageView)
  }

  private fun createProgressBar(context: Context) =
      CircularProgressDrawable(context).apply {
        setColorSchemeColors(
            R.color.primaryColor, R.color.primaryDarkColor, R.color.secondaryDarkColor)
        centerRadius = 30f
        strokeWidth = 5f
        start()
      }
}
