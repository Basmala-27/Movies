package com.moviecoo.colorthemeandtypography.ui.screens.searchScreen.repository

import android.util.Log
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.api.MovieApi
import com.moviecoo.colorthemeandtypography.mapper.toMoviesUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel
import javax.inject.Inject

// This repository will handle both the detail ID lookup for voice nav
// and the general search for the SearchScreen.
class SearchRepository @Inject constructor(
    private val movieApi: MovieApi // Inject your Retrofit service
) {
    /**
     * Searches The Movie Database by title and returns the list of matching movies.
     */
    suspend fun searchMoviesByTitle(title: String): List<MovieUiModel> {
        if (title.isBlank()) return emptyList()

        return try {
            // Note: Your Retrofit service needs a @GET("search/movie") function
            val response = movieApi.searchMovie(query = title)
            if (response.isSuccessful) {
                val movies = response.body()?.toMoviesUiModel() ?: emptyList()

                // Check 3: What is the API response body?
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

    /**
     * Searches The Movie Database by title and returns the ID of the first match.
     * Used specifically by the Voice Navigation feature.
     */
    suspend fun getMovieIdByTitle(title: String): Int? {
        if (title.isBlank()) return null

        return try {
            val response = movieApi.searchMovie(query = title)
            if (response.isSuccessful) {
                // Assuming your MovieDataModel has a 'results' list where elements have an 'id'
                response.body()?.results?.firstOrNull()?.id
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}