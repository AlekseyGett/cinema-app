package com.github.alekseygett.cinemaapp.feature.movies.di

import com.github.alekseygett.cinemaapp.feature.movies.data.MoviesRepository
import com.github.alekseygett.cinemaapp.feature.movies.data.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface MoviesBindModule {
    @Binds
    fun bindMoviesRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository
}