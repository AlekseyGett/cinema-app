package com.github.alekseygett.cinemaapp.feature.details.ui

import com.github.alekseygett.cinemaapp.base.BaseViewModel
import com.github.alekseygett.cinemaapp.base.Event
import com.github.alekseygett.cinemaapp.domain.models.Movie

class MovieDetailsViewModel(private val movie: Movie) : BaseViewModel<ViewState>() {

    override fun initialViewState() = ViewState(
        movie = movie
    )

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            else -> return null
        }
    }

}