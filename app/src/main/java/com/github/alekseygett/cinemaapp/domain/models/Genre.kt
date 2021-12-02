package com.github.alekseygett.cinemaapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val name: String
) : Parcelable