package com.github.alekseygett.cinemaapp.feature.movies.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.alekseygett.cinemaapp.R
import com.github.alekseygett.cinemaapp.databinding.FragmentMoviesBinding
import com.github.alekseygett.cinemaapp.feature.details.ui.MovieDetailsFragment
import com.github.alekseygett.cinemaapp.utils.loadData
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private val binding: FragmentMoviesBinding by viewBinding()
    private val viewModel: MoviesViewModel by viewModel()

    private val moviesAdapter by lazy {
        ListDelegationAdapter(
            moviesAdapterDelegate { movie ->
                viewModel.processUiEvent(UiEvent.OnMoviePosterClick(movie))
            }
        )
    }

    companion object {
        fun newInstance() = MoviesFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewPager()

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        viewModel.processUiEvent(UiEvent.OnMoviesRequest)
    }

    private fun initViewPager() {
        binding.moviesCarousel.apply {
            adapter = moviesAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }

        binding.dotsIndicator.setViewPager2(binding.moviesCarousel)
    }

    private fun render(viewState: ViewState) {
        binding.loadingIndicatorWrapper.isGone = !viewState.isLoading

        moviesAdapter.loadData(viewState.movies)

        viewState.singleEvent?.getUnhandledEventOrNull()?.let { event -> handleSingleEvent(event) }
    }

    private fun handleSingleEvent(event: SingleEvent) {
        when (event) {
            is SingleEvent.OpenMovieDetailsScreen -> {
                val fragment = MovieDetailsFragment.newInstance(event.movie)
                navigateTo(fragment)
            }
            is SingleEvent.ShowErrorMessage -> {
                showErrorMessage(event.errorMessage)
            }
        }
    }

    private fun navigateTo(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager

        fragmentManager
            .beginTransaction()
            .replace(android.R.id.content, fragment)
            .commit()
    }

    private fun showErrorMessage(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
    }

}