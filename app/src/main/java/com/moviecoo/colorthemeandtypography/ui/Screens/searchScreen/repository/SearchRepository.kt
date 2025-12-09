package com.moviecoo.colorthemeandtypography.ui.screens.searchScreen.repository

import android.util.Log
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.api.MovieApi
import com.moviecoo.colorthemeandtypography.mapper.toMoviesUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val movieApi: MovieApi
) {

    suspend fun searchMoviesByTitle(title: String): List<MovieUiModel> {
        if (title.isBlank()) return emptyList()

        return try {

            val response = movieApi.searchMovie(query = title)
            if (response.isSuccessful) {
                val movies = response.body()?.toMoviesUiModel() ?: emptyList()

                Log.d("SearchRepo", "API Success. Body null? ${response.body() == null}. Mapped count: ${movies.size}") // <--- ADD LOG

                return movies
            } else {
                Log.e("SearchRepo", "API Search Failed. Code: ${response.code()}. Message: ${response.errorBody()?.string()}") // <--- ADD LOG
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("SearchRepo", "Network exception during search: ${e.message}") // <--- ADD LOG
            emptyList()
        }
    }

    suspend fun getMovieIdByTitle(title: String): Int? {
        if (title.isBlank()) return null

        return try {
            val response = movieApi.searchMovie(query = title)
            if (response.isSuccessful) {
                response.body()?.results?.firstOrNull()?.id
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}