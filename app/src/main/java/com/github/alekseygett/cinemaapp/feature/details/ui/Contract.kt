package com.github.alekseygett.cinemaapp.feature.details.ui

import com.github.alekseygett.cinemaapp.domain.models.Movie

data class ViewState(
    val movie: Movie
)

sealed class UiEvent {

}

sealed class DataEvent {

}