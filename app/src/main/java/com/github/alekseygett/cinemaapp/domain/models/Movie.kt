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
) : Parcelable {

    companion object {
        val empty = Movie(
            id = -1,
            title = "",
            overview = "",
            releaseDate = Date(),
            originalTitle = "",
            originalLanguage = "",
            adult = false,
            genres = emptyList(),
            popularity = 0.0,
            averageVote = 0.0,
            voteCount = 0,
            posterUrl = "",
            videoUrl = ""
        )
    }

}

