package com.github.alekseygett.cinemaapp.feature.movies.ui

import com.github.alekseygett.cinemaapp.databinding.ItemMovieBinding
import com.github.alekseygett.cinemaapp.feature.movies.domain.model.Movie
import com.github.alekseygett.cinemaapp.utils.loadImage
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun moviesAdapterDelegate(onClick: (Movie) -> Unit) =
    adapterDelegateViewBinding<Movie, Movie, ItemMovieBinding>(
        viewBinding = { layoutInflater, parent ->
            ItemMovieBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        binding.posterImage.setOnClickListener { onClick(item) }

        bind {
            binding.posterImage.loadImage(item.posterUrl)
        }
    }