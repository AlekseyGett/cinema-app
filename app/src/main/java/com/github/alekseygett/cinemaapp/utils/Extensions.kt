package com.github.alekseygett.cinemaapp.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.github.alekseygett.cinemaapp.R
import com.hannesdorfmann.adapterdelegates4.AbsDelegationAdapter

private const val DEFAULT_THROTTLE_DELAY = 300L

fun View.setThrottledClickListener(delay: Long = DEFAULT_THROTTLE_DELAY, onClick: (View) -> Unit) {
    setOnClickListener {
        throttle(delay) {
            onClick(it)
        }
    }
}

private var lastClickTimestamp = 0L
fun throttle(delay: Long = DEFAULT_THROTTLE_DELAY, action: () -> Unit): Boolean {
    val currentTimestamp = System.currentTimeMillis()
    val delta = currentTimestamp - lastClickTimestamp
    if (delta !in 0L..delay) {
        lastClickTimestamp = currentTimestamp
        action()
        return true
    }
    return false
}

fun ImageView.loadImage(
    src: String?,
    @DrawableRes errorRes: Int = R.drawable.broken_image,
    @DrawableRes placeholderRes: Int = R.drawable.image_placeholder,
    config: RequestBuilder<Drawable>.() -> Unit = {}
) {
    Glide
        .with(context)
        .load(src)
        .error(errorRes)
        .placeholder(placeholderRes)
        .apply { config(this) }
        .into(this)
}

fun <T> AbsDelegationAdapter<List<T>>.loadData(data: List<T>) {
    items = data
    notifyDataSetChanged()
}