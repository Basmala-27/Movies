package com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.api

import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.MovieResponse
import com.moviecoo.colorthemeandtypography.data.Constants.Companion.MOVIE_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {
    @GET(MOVIE_ENDPOINT)
    suspend fun fetchUpcomingMovies(): Response<MovieResponse>
}
