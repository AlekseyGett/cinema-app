package com.github.alekseygett.cinemaapp.feature.movies.di

import com.github.alekseygett.cinemaapp.feature.movies.data.MoviesRepository
import com.github.alekseygett.cinemaapp.feature.movies.data.MoviesRepositoryImpl
import com.github.alekseygett.cinemaapp.feature.movies.data.api.MoviesApi
import com.github.alekseygett.cinemaapp.feature.movies.data.api.MoviesRemoteSource
import com.github.alekseygett.cinemaapp.feature.movies.domain.MoviesInteractor
import com.github.alekseygett.cinemaapp.feature.movies.ui.MoviesViewModel
import com.github.terrakok.cicerone.Router
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://gist.githubusercontent.com/"

val moviesModule = module {
    single<OkHttpClient> {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    single<MoviesApi> {
        get<Retrofit>().create(MoviesApi::class.java)
    }

    single<MoviesRemoteSource> {
        MoviesRemoteSource(get<MoviesApi>())
    }

    single<MoviesRepository> {
        MoviesRepositoryImpl(get<MoviesRemoteSource>())
    }

    single<MoviesInteractor> {
        MoviesInteractor(get<MoviesRepository>())
    }

    viewModel<MoviesViewModel> {
        MoviesViewModel(get<MoviesInteractor>(), get<Router>())
    }
}