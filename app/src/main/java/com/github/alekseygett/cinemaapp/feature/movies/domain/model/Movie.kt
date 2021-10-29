package com.github.alekseygett.cinemaapp.feature.movies.domain.model

import java.util.*

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
)