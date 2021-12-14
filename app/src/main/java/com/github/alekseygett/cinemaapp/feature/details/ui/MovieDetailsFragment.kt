package com.github.alekseygett.cinemaapp.feature.details.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.alekseygett.cinemaapp.R
import com.github.alekseygett.cinemaapp.databinding.FragmentMovieBinding
import com.github.alekseygett.cinemaapp.domain.models.Movie
import com.github.alekseygett.cinemaapp.utils.loadData
import com.github.alekseygett.cinemaapp.utils.loadImage
import com.github.alekseygett.cinemaapp.utils.setThrottledClickListener
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.util.*

class MovieDetailsFragment : Fragment(R.layout.fragment_movie) {

    companion object {
        private const val MOVIE_KEY = "movie"

        fun newInstance(movie: Movie) = MovieDetailsFragment().apply {
            arguments = bundleOf(MOVIE_KEY to movie)
        }
    }

    private val viewModel: MovieDetailsViewModel by viewModel {
        parametersOf(requireArguments().getParcelable<Movie>(MOVIE_KEY))
    }

    private val binding: FragmentMovieBinding by viewBinding()

    private val genresAdapter by lazy {
        ListDelegationAdapter(genresAdapterDelegate())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.genresList.adapter = genresAdapter

        binding.playButton.setThrottledClickListener {
            viewModel.processUiEvent(UiEvent.OnPlayButtonClick)
        }

        binding.toolbar.setNavigationOnClickListener {
            viewModel.processUiEvent(UiEvent.OnBackButtonClick)
        }

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    private fun render(viewState: ViewState) {
        viewState.movie.let { movie ->
            binding.toolbar.title = movie.title
            binding.posterImage.loadImage(movie.posterUrl)
            genresAdapter.loadData(movie.genres)
            binding.overviewText.text = movie.overview
            binding.releaseDate.text = formatDate(movie.releaseDate)
            binding.originalTitle.text = movie.originalTitle
            binding.originalLanguage.text = movie.originalLanguage
            binding.votesText.text = formatVotes(movie.averageVote, movie.voteCount)
            binding.popularityText.text = formatPopularity(movie.popularity)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(date: Date): String =
        SimpleDateFormat("dd.MM.yyyy").format(date)

    private fun formatVotes(averageVote: Double, voteCount: Int): String =
        requireContext().getString(R.string.votes_text, averageVote, voteCount)

    private fun formatPopularity(popularity: Double): String =
        requireContext().getString(R.string.popularity_text, popularity)
}
