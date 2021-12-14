package com.github.alekseygett.cinemaapp.feature.details.ui

import com.github.alekseygett.cinemaapp.Screens
import com.github.alekseygett.cinemaapp.base.BaseViewModel
import com.github.alekseygett.cinemaapp.base.Event
import com.github.alekseygett.cinemaapp.domain.models.Movie
import com.github.terrakok.cicerone.Router

class MovieDetailsViewModel(
    movie: Movie,
    private val router: Router
) : BaseViewModel<ViewState>() {

    init {
        processDataEvent(DataEvent.OnLoadData(movie))
    }

    override fun initialViewState() = ViewState(
        movie = Movie.empty
    )

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.OnPlayButtonClick -> {
                router.navigateTo(Screens.player(previousState.movie))
                return null
            }
            is UiEvent.OnBackButtonClick -> {
                router.exit()
                return null
            }
            is DataEvent.OnLoadData -> {
                return previousState.copy(movie = event.movie)
            }
            else -> return null
        }
    }

}