package com.github.alekseygett.cinemaapp.feature.movies.domain

import com.github.alekseygett.cinemaapp.feature.movies.data.MoviesRepository
import com.github.alekseygett.cinemaapp.utils.attempt

class MoviesInteractor(private val repository: MoviesRepository) {
    suspend fun getMovies() = attempt {
        repository.getMovies()
    }
}