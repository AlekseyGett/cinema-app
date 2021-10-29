package com.github.alekseygett.cinemaapp.feature.movies.data.model

import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("genres")
    val genres: List<GenreModel>,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("vote_average")
    val averageVote: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("poster_path")
    val posterUrl: String,
    @SerializedName("video")
    val videoUrl: String
)