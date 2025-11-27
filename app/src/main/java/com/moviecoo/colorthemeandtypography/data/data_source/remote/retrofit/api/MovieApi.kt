package com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.api
import com.moviecoo.colorthemeandtypography.data.Constants.Companion.MOVIE_ENDPOINT
import com.moviecoo.colorthemeandtypography.data.Constants.Companion.MOVIE_NOW_PLAYING_ENDPOINT
import com.moviecoo.colorthemeandtypography.data.Constants.Companion.MOVIE_TOP_RATED_ENDPOINT
import com.moviecoo.colorthemeandtypography.data.Constants.Companion.MOVIE_UPCOMING_ENDPOINT
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.MovieDataModel
import com.moviecoo.colorthemeandtypography.mapper.MovieContentDto
import com.moviecoo.colorthemeandtypography.mapper.VideoResponse
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.data.MovieDetailsDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET(MOVIE_ENDPOINT)
    suspend fun fetchMovies(): Response<MovieDataModel>
    @GET(MOVIE_TOP_RATED_ENDPOINT)
    suspend fun fetchTopRatingMovies(): Response<MovieDataModel>
    @GET(MOVIE_NOW_PLAYING_ENDPOINT)
    suspend fun fetchNowPlayingMovies(): Response<MovieDataModel>
    @GET(MOVIE_UPCOMING_ENDPOINT)
    suspend fun fetchUpcomingMovies(): Response<MovieDataModel>
    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("api_key") apiKey: String = "c49820c55b9cfe9e135e6427800d7597",
        @Query("with_genres") genreIds: String,
        @Query("sort_by") sortBy: String = "popularity.desc", // ترتيب حسب الشهرة
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MovieDataModel>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "c49820c55b9cfe9e135e6427800d7597"
    ): Response<MovieDetailsDataModel>


        @GET("movie/{id}")
        suspend fun getMovieContent(
            @Path("id") movieId: Int,
            @Query("api_key") apiKey: String = "YOUR_API_KEY"
        ): MovieContentDto


    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "c49820c55b9cfe9e135e6427800d7597"
    ): Response<VideoResponse>


    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String = "c49820c55b9cfe9e135e6427800d7597",
        @Query("query") query: String
    ): Response<MovieDataModel>


}
