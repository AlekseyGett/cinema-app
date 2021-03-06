package com.github.alekseygett.cinemaapp.feature.movies.data

import com.github.alekseygett.cinemaapp.feature.movies.data.api.MoviesRemoteSource
import com.github.alekseygett.cinemaapp.domain.models.Movie
import com.github.alekseygett.cinemaapp.feature.movies.domain.toDomainModel

class MoviesRepositoryImpl(private val dataSource: MoviesRemoteSource) : MoviesRepository {
    override suspend fun getMovies(): List<Movie> = dataSource.getMovies().toDomainModel()
}