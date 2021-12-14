package com.github.alekseygett.cinemaapp.feature.movies.ui

import com.github.alekseygett.cinemaapp.databinding.ItemMovieBinding
import com.github.alekseygett.cinemaapp.domain.models.Movie
import com.github.alekseygett.cinemaapp.utils.loadImage
import com.github.alekseygett.cinemaapp.utils.setThrottledClickListener
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import java.util.*

fun moviesAdapterDelegate(onClick: (Movie) -> Unit) =
    adapterDelegateViewBinding<Movie, Movie, ItemMovieBinding>(
        viewBinding = { layoutInflater, parent ->
            ItemMovieBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        binding.root.setThrottledClickListener { onClick(item) }

        bind {
            binding.posterImage.loadImage(item.posterUrl)
            binding.titleTextView.text = item.title

            val calendar = Calendar.getInstance().apply { time = item.releaseDate }
            binding.yearTextView.text = calendar.get(Calendar.YEAR).toString()
        }
    }