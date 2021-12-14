package com.github.alekseygett.cinemaapp

import android.app.Application
import com.github.alekseygett.cinemaapp.di.appModule
import com.github.alekseygett.cinemaapp.feature.details.di.movieDetailsModule
import com.github.alekseygett.cinemaapp.feature.movies.di.moviesModule
import com.github.alekseygett.cinemaapp.feature.player.di.playerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(applicationContext)

            modules(appModule, moviesModule, movieDetailsModule, playerModule)
        }
    }

}