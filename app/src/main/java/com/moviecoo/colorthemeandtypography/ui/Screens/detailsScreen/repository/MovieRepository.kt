package com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.repository

import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.api.MovieApi
import com.moviecoo.colorthemeandtypography.mapper.toMovieDetailsUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.data.MovieDetailsUiModel
import dagger.hilt.android.lifecycle.HiltViewModel


class MovieDetailsRepository(private val api: MovieApi) {

    suspend fun getMovieDetails(movieId: Int): MovieDetailsUiModel {
        val response = api.getMovieDetails(movieId)
        if (response.isSuccessful) {
            val body = response.body() ?: throw Exception("Empty response")
            return body.toMovieDetailsUiModel()
        } else {
            throw Exception("API Error: ${response.code()}")
        }
    }

    // إضافة دالة لجلب الفيديو (Trailer)
    suspend fun getMovieTrailer(movieId: Int): String? {
        val response = api.getMovieVideos(movieId)
        if (response.isSuccessful) {
            val videos = response.body()?.results

            // 1. Filter only videos that are marked as 'Trailer' type and from 'YouTube'.
            val youtubeTrailers = videos
                ?.filter { it.type == "Trailer" && it.site == "YouTube" }
                ?: return null // Return null if the list is empty

            // 2. Find the best possible trailer: prioritize based on name and quality.
            val bestTrailer = youtubeTrailers
                // Check if the name contains "Official Trailer" (case-insensitive)
                .firstOrNull { it.name.contains("Official Trailer", ignoreCase = true) }

            // 3. If no 'Official Trailer' is found, fall back to the first video marked simply as 'Trailer'.
            return bestTrailer?.key ?: youtubeTrailers.firstOrNull()?.key
        }

        // Return null if API call was unsuccessful
        return null
    }
}
