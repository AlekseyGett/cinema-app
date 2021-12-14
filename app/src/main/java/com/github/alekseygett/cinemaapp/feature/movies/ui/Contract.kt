package com.github.alekseygett.cinemaapp.feature.movies.ui

import com.github.alekseygett.cinemaapp.base.Event
import com.github.alekseygett.cinemaapp.domain.models.Movie

data class ViewState(
    val movies: List<Movie>,
    val isLoading: Boolean
)

sealed class UiEvent : Event {
    object OnMoviesRequest : UiEvent()
    data class OnMoviePosterClick(val movie: Movie) : UiEvent()
}

sealed class DataEvent : Event {
    object OnLoadData : DataEvent()
    data class OnSuccessMoviesRequest(val movies: List<Movie>) : DataEvent()
    data class OnFailureMoviesRequest(val errorMessage: String) : DataEvent()
}

sealed class SingleEvent {
    data class ShowErrorMessage(val errorMessage: String) : SingleEvent()
}
