package com.github.alekseygett.cinemaapp.feature.player.di

import com.github.alekseygett.cinemaapp.feature.player.service.MediaDescriptionAdapter
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val playerModule = module {
    factory<ExoPlayer> {
        ExoPlayer.Builder(androidContext()).build()
    }

    factory<PlayerNotificationManager.MediaDescriptionAdapter> {
        MediaDescriptionAdapter(androidContext())
    }
}