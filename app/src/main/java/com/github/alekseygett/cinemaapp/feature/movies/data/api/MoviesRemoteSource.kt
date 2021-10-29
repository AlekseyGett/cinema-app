package com.github.alekseygett.cinemaapp.feature.movies.data.api

import com.github.alekseygett.cinemaapp.feature.movies.data.model.MoviesModel

class MoviesRemoteSource(private val api: MoviesApi) {
    suspend fun getMovies(): MoviesModel = api.getMovies()
}