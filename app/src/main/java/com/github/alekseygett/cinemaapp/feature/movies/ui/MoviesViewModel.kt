package com.github.alekseygett.cinemaapp.feature.movies.ui

import com.github.alekseygett.cinemaapp.base.BaseViewModel
import com.github.alekseygett.cinemaapp.base.Event
import com.github.alekseygett.cinemaapp.feature.movies.domain.MoviesInteractor

class MoviesViewModel(private val interactor: MoviesInteractor) : BaseViewModel<ViewState>() {

    override fun initialViewState() = ViewState(
        movies = emptyList()
    )

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        TODO()
    }

}