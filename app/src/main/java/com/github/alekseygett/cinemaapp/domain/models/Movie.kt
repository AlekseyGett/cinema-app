package com.github.alekseygett.cinemaapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: Date,
    val originalTitle: String,
    val originalLanguage: String,
    val adult: Boolean,
    val genres: List<Genre>,
    val popularity: Double,
    val averageVote: Double,
    val voteCount: Int,
    val posterUrl: String,
    val videoUrl: String
) : Parcelable