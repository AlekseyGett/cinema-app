package com.github.alekseygett.cinemaapp.feature.movies.ui

import com.github.alekseygett.cinemaapp.Screens
import com.github.alekseygett.cinemaapp.base.BaseViewModel
import com.github.alekseygett.cinemaapp.base.Event
import com.github.alekseygett.cinemaapp.feature.movies.domain.MoviesInteractor
import com.github.alekseygett.cinemaapp.utils.MutableSingleLiveEvent
import com.github.alekseygett.cinemaapp.utils.SingleLiveEvent
import com.github.terrakok.cicerone.Router

class MoviesViewModel(
    private val interactor: MoviesInteractor,
    private val router: Router
) : BaseViewModel<ViewState>() {

    init {
        processUiEvent(UiEvent.OnMoviesRequest)
    }

    private val _singleEvent = MutableSingleLiveEvent<SingleEvent>()

    val singleEvent: SingleLiveEvent<SingleEvent>
        get() = _singleEvent

    override fun initialViewState() = ViewState(
        movies = emptyList(),
        isLoading = false
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

                return null
            }
            is UiEvent.OnMoviePosterClick -> {
                router.navigateTo(Screens.movieDetails(event.movie))
                return null
            }
            is DataEvent.OnLoadData -> {
                return previousState.copy(isLoading = true)
            }
            is DataEvent.OnSuccessMoviesRequest -> {
                return previousState.copy(movies = event.movies, isLoading = false)
            }
            is DataEvent.OnFailureMoviesRequest -> {
                val showErrorMessageEvent = SingleEvent.ShowErrorMessage(event.errorMessage)
                _singleEvent.postValue(showErrorMessageEvent)
                return previousState.copy(isLoading = false)
            }
            else -> return null
        }
    }

}