package com.github.alekseygett.cinemaapp.feature.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.alekseygett.cinemaapp.base.BaseViewModel
import com.github.alekseygett.cinemaapp.base.Event
import com.github.alekseygett.cinemaapp.domain.models.Movie
import com.github.alekseygett.cinemaapp.feature.details.domain.MovieDetailsInteractor
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import java.util.*

class MovieDetailsViewModel(
    private val interactor: MovieDetailsInteractor,
    private val movie: Movie
) : BaseViewModel<ViewState>() {

    override fun initialViewState() = ViewState(
        movie = Movie(
            id = -1,
            title = "",
            overview = "",
            releaseDate = Date(),
            originalTitle = "",
            originalLanguage = "",
            adult = false,
            genres = emptyList(),
            popularity = 0.0,
            averageVote = 0.0,
            voteCount = 0,
            posterUrl = "",
            videoUrl = ""
        )
    )

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is DataEvent.LoadData -> {
                return previousState.copy(movie = event.movie)
            }
            else -> return null
        }
    }

    class Factory @AssistedInject constructor(
        private val interactor: MovieDetailsInteractor,
        @Assisted private val movie: Movie
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == MovieDetailsViewModel::class.java)
            return MovieDetailsViewModel(interactor, movie) as T
        }

    }

    @AssistedFactory
    interface FactoryProvider {
        fun provide(@Assisted movie: Movie): MovieDetailsViewModel.Factory
    }

}