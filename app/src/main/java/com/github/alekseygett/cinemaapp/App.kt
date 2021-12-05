package com.github.alekseygett.cinemaapp

import android.app.Application
import com.github.alekseygett.cinemaapp.di.AppComponent
import com.github.alekseygett.cinemaapp.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

}

