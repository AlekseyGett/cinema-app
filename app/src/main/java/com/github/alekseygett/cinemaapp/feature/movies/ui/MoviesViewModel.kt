package com.github.alekseygett.cinemaapp.feature.movies.ui

import com.github.alekseygett.cinemaapp.base.BaseViewModel
import com.github.alekseygett.cinemaapp.base.Event
import com.github.alekseygett.cinemaapp.feature.movies.domain.MoviesInteractor

class MoviesViewModel(private val interactor: MoviesInteractor) : BaseViewModel<ViewState>() {

    override fun initialViewState() = ViewState(
        movies = emptyList(),
        isLoading = false,
        errorMessage = null
    )

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.OnMoviesRequest -> {
                processDataEvent(DataEvent.OnLoadData)

                interactor.getMovies().fold(
                    onSuccess = { movies ->
                        processDataEvent(DataEvent.OnSuccessMoviesRequest(movies))
                    },
                    onError = { error ->
                        val errorMessage = error.localizedMessage ?: ""
                        processDataEvent(DataEvent.OnFailureMoviesRequest(errorMessage))
                    }
                )
            }
            is DataEvent.OnLoadData -> {
                return previousState.copy(isLoading = true)
            }
            is DataEvent.OnSuccessMoviesRequest -> {
                return previousState.copy(movies = event.movies, isLoading = false)
            }
            is DataEvent.OnFailureMoviesRequest -> {
                return previousState.copy(errorMessage = event.errorMessage, isLoading = false)
            }
        }

        return null
    }

}