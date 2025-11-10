package com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.api
import com.moviecoo.colorthemeandtypography.data.Constants.Companion.MOVIE_ENDPOINT
import com.moviecoo.colorthemeandtypography.data.Constants.Companion.MOVIE_NOW_PLAYING_ENDPOINT
import com.moviecoo.colorthemeandtypography.data.Constants.Companion.MOVIE_TOP_RATED_ENDPOINT
import com.moviecoo.colorthemeandtypography.data.Constants.Companion.MOVIE_UPCOMING_ENDPOINT
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.MovieDataModel
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {
    @GET(MOVIE_ENDPOINT)
    suspend fun fetchMovies(): Response<MovieDataModel>
    @GET(MOVIE_TOP_RATED_ENDPOINT)
    suspend fun fetchTopRatingMovies(): Response<MovieDataModel>
    @GET(MOVIE_NOW_PLAYING_ENDPOINT)
    suspend fun fetchNowPlayingMovies(): Response<MovieDataModel>
    @GET(MOVIE_UPCOMING_ENDPOINT)
    suspend fun fetchUpcomingMovies(): Response<MovieDataModel>

}
