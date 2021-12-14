package com.github.alekseygett.cinemaapp.feature.details.ui

import com.github.alekseygett.cinemaapp.databinding.ItemGenreBinding
import com.github.alekseygett.cinemaapp.domain.models.Genre
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun genresAdapterDelegate() =
    adapterDelegateViewBinding<Genre, Genre, ItemGenreBinding>(
        viewBinding = { layoutInflater, parent ->
            ItemGenreBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {
            binding.title.text = item.name
        }
    }