package com.github.alekseygett.cinemaapp.feature.details.ui

import com.github.alekseygett.cinemaapp.base.Event
import com.github.alekseygett.cinemaapp.domain.models.Movie

data class ViewState(
    val movie: Movie
)

sealed class UiEvent : Event {
    object OnPlayButtonClick : UiEvent()
    object OnBackButtonClick : UiEvent()
}

sealed class DataEvent : Event {
    data class OnLoadData(val movie: Movie) : DataEvent()
}