package com.github.alekseygett.cinemaapp

import com.github.alekseygett.cinemaapp.domain.models.Movie
import com.github.alekseygett.cinemaapp.feature.details.ui.MovieDetailsFragment
import com.github.alekseygett.cinemaapp.feature.movies.ui.MoviesFragment
import com.github.alekseygett.cinemaapp.feature.player.ui.PlayerFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun movies() = FragmentScreen {
        MoviesFragment.newInstance()
    }

    fun movieDetails(movie: Movie) = FragmentScreen {
        MovieDetailsFragment.newInstance(movie)
    }

    fun player(movie: Movie) = FragmentScreen {
        PlayerFragment.newInstance(movie)
    }

}