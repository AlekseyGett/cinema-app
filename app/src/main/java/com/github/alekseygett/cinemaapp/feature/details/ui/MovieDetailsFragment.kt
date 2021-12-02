package com.github.alekseygett.cinemaapp.feature.details.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.alekseygett.cinemaapp.R
import com.github.alekseygett.cinemaapp.databinding.FragmentMovieBinding
import com.github.alekseygett.cinemaapp.domain.models.Movie
import com.github.alekseygett.cinemaapp.utils.loadImage
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    private fun render(viewState: ViewState) {
        binding.posterImage.loadImage(viewState.movie.posterUrl)
    }

}