package com.github.alekseygett.cinemaapp.di

import com.github.alekseygett.cinemaapp.feature.details.ui.MovieDetailsFragment
import com.github.alekseygett.cinemaapp.feature.movies.di.MoviesModule
import com.github.alekseygett.cinemaapp.feature.movies.ui.MoviesFragment
import dagger.Component

@Component(modules = [MoviesModule::class])
interface AppComponent {
    fun inject(moviesFragment: MoviesFragment)
    fun inject(movieDetailsFragment: MovieDetailsFragment)
}