package com.github.alekseygett.cinemaapp.feature.movies.data

import com.github.alekseygett.cinemaapp.domain.models.Movie

interface MoviesRepository {
    suspend fun getMovies(): List<Movie>
}