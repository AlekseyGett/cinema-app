package com.github.alekseygett.cinemaapp.feature.details.di

import com.github.alekseygett.cinemaapp.feature.details.ui.MovieDetailsViewModel
import com.github.alekseygett.cinemaapp.domain.models.Movie
import com.github.terrakok.cicerone.Router
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieDetailsModule = module {
    viewModel<MovieDetailsViewModel> { parameters ->
        MovieDetailsViewModel(parameters.get<Movie>(), get<Router>())
    }
}