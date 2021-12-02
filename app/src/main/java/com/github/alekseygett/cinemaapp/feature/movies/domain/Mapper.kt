package com.github.alekseygett.cinemaapp.feature.movies.domain

import com.github.alekseygett.cinemaapp.feature.movies.data.model.GenreModel
import com.github.alekseygett.cinemaapp.feature.movies.data.model.MovieModel
import com.github.alekseygett.cinemaapp.feature.movies.data.model.MoviesModel
import com.github.alekseygett.cinemaapp.domain.models.Genre
import com.github.alekseygett.cinemaapp.domain.models.Movie
import java.text.SimpleDateFormat
import java.util.*

fun MoviesModel.toDomainModel(): List<Movie> = results.map(MovieModel::toDomainModel)

fun MovieModel.toDomainModel(): Movie = Movie(
    id = id,
    title = title,
    overview = overview,
    releaseDate = parseDate(releaseDate) ?: mappingError("Incorrect format of date"),
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    adult = adult,
    genres = genres.map(GenreModel::toDomainModel),
    popularity = popularity,
    averageVote = averageVote,
    voteCount = voteCount,
    posterUrl = posterUrl,
    videoUrl = videoUrl
)

fun GenreModel.toDomainModel(): Genre = Genre(name)

private fun parseDate(dateStr: String): Date? {
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    return formatter.parse(dateStr)
}

private fun mappingError(message: String): Nothing {
    throw IllegalArgumentException(message)
}