package com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.videoRepository

import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.api.MovieApi

class VideoRepository(private val api: MovieApi) {
    suspend fun getMovieTrailer(movieId: Int): String? {
        val response = api.getMovieVideos(movieId)
        if (response.isSuccessful) {
            return response.body()?.results
                ?.firstOrNull { it.type == "Trailer" && it.site == "YouTube" }
                ?.key
        }
        return null
    }
}