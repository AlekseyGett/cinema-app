package com.github.alekseygett.cinemaapp.feature.movies.ui

import com.github.alekseygett.cinemaapp.feature.movies.domain.model.Movie

data class ViewState(
    val movies: List<Movie>
)

sealed class UiEvent {

}

sealed class DataEvent {

}