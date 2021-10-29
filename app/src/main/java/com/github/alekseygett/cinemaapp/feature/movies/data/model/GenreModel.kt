package com.github.alekseygett.cinemaapp.feature.movies.data.model

import com.google.gson.annotations.SerializedName

data class GenreModel(
    @SerializedName("name")
    val name: String
)