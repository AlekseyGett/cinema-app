package com.github.alekseygett.cinemaapp.feature.movies.di

import dagger.Module

@Module(includes = [MoviesNetworkModule::class, MoviesBindModule::class])
class MoviesModule