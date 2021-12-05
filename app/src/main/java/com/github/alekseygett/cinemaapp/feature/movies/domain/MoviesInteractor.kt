package com.github.alekseygett.cinemaapp.feature.movies.domain

import com.github.alekseygett.cinemaapp.feature.movies.data.MoviesRepository
import com.github.alekseygett.cinemaapp.utils.attempt
import javax.inject.Inject

class MoviesInteractor @Inject constructor(private val repository: MoviesRepository) {
    suspend fun getMovies() = attempt { repository.getMovies() }
}