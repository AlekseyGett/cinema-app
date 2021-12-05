package com.github.alekseygett.cinemaapp.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.github.alekseygett.cinemaapp.App
import com.github.alekseygett.cinemaapp.R
import com.github.alekseygett.cinemaapp.di.AppComponent
import com.hannesdorfmann.adapterdelegates4.AbsDelegationAdapter

val Context.appComponent: AppComponent
    get() = when(this) {
        is App -> this.appComponent
        else -> this.applicationContext.appComponent
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