package com.github.alekseygett.cinemaapp.feature.movies.data.api

import com.github.alekseygett.cinemaapp.feature.movies.data.model.MoviesModel
import javax.inject.Inject

class MoviesRemoteSource @Inject constructor(private val api: MoviesApi) {
    suspend fun getMovies(): MoviesModel = api.getMovies()
}