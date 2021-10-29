package com.github.alekseygett.cinemaapp.feature.movies.data

import com.github.alekseygett.cinemaapp.feature.movies.domain.model.Movie

interface MoviesRepository {
    suspend fun getMovies(): List<Movie>
}