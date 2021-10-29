package com.github.alekseygett.cinemaapp.feature.movies.data.model

import com.google.gson.annotations.SerializedName

data class MoviesModel(
    @SerializedName("results")
    val results: List<MovieModel>,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)