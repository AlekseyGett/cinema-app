package com.github.alekseygett.cinemaapp.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import org.koin.dsl.module

val appModule = module {
    single<Cicerone<Router>> {
        Cicerone.create()
    }

    single<Router> {
        get<Cicerone<Router>>().router
    }

    single<NavigatorHolder> {
        get<Cicerone<Router>>().getNavigatorHolder()
    }
}