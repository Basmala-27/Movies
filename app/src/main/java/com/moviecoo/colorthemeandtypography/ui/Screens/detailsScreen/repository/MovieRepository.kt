package com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.repository

import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.api.MovieApi
import com.moviecoo.colorthemeandtypography.mapper.toMovieDetailsUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.data.MovieDetailsUiModel

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
}
