package com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.moodToMovie

import com.moviecoo.colorthemeandtypography.data.Constants.Companion.IMAGE_ENDPOINT
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.api.MovieApi
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel

class MoodToMovieRepository (private val api: MovieApi){


        suspend fun getMoviesByMood(genreIds: String): List<MovieUiModel> {
            val response = api.getMoviesByGenre(genreIds = genreIds)

            if (response.isSuccessful) {
                return response.body()?.results?.map { movie ->

                    val releaseDate = movie.release_date

                    val safeYear = if (releaseDate.length >= 4) {
                        releaseDate.substring(0, 4)
                    } else {
                        "Unknown Year"
                    }
                    MovieUiModel(
                        id = movie.id,
                        title = movie.title,
                        year = safeYear,
                       description = movie.overview,
                        genre = movie.genre_ids.toString(),
                        rating = movie.vote_average,
                        image = IMAGE_ENDPOINT + "${movie.poster_path}"
                    )

                } ?: emptyList()
            } else {
                throw Exception("API Error: ${response.code()}")
            }
        }
    suspend fun getRandomMovie(): MovieUiModel? {
        val genres = listOf("28", "35", "18", "27") // Action, Comedy, Drama, Horror
        val genreId = genres.random()
        val movies = getMoviesByMood(genreId)
        return movies.randomOrNull()
    }
}


