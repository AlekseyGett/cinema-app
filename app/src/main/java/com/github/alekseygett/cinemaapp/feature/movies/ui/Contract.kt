package com.github.alekseygett.cinemaapp.feature.movies.ui

import com.github.alekseygett.cinemaapp.base.Event
import com.github.alekseygett.cinemaapp.feature.movies.domain.model.Movie

data class ViewState(
    val movies: List<Movie>,
    val isLoading: Boolean,
    val errorMessage: String?
)

sealed class UiEvent : Event {
    object OnMoviesRequest : UiEvent()
    object OnErrorMessageShowed : UiEvent()
}

sealed class DataEvent : Event {
    object OnLoadData : DataEvent()
    data class OnSuccessMoviesRequest(val movies: List<Movie>) : DataEvent()
    data class OnFailureMoviesRequest(val errorMessage: String) : DataEvent()
}