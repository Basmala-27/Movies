package com.moviecoo.colorthemeandtypography.repository

//import com.moviecoo.colorthemeandtypography.data.data_source.remote.RemoteDataSource
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.api.MovieApi
import com.moviecoo.colorthemeandtypography.domain.model.MoviesDomainModel
import com.moviecoo.colorthemeandtypography.domain.repository.MoviesRepository
import com.moviecoo.colorthemeandtypography.mapper.toMoviesDomainModel
import javax.inject.Inject

//class MoviesRepositoryImp( private val remoteDataSource: RemoteDataSource): MoviesRepository{
//    override fun fetchMovies(): List<MoviesDomainModel> {
//       return remoteDataSource.fetchMovies().toMoviesDomainModel()
//    }
//
//
//}
//


class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MoviesRepository {





    override suspend fun fetchMovies(): List<MoviesDomainModel> {
        val response = api.fetchMovies()
        val movieData = response.body()

        return movieData?.toMoviesDomainModel() ?: emptyList()
    }






    suspend fun fetchTopRatedMovies(): List<MoviesDomainModel> {
        val response = api.fetchTopRatingMovies()
        val movieData = response.body()
        return movieData?.toMoviesDomainModel() ?: emptyList()
    }

    suspend fun fetchNowPlayingMovies(): List<MoviesDomainModel> {
        val response = api.fetchNowPlayingMovies()
        val movieData = response.body()
        return movieData?.toMoviesDomainModel() ?: emptyList()
    }

    suspend fun fetchUpcomingMovies(): List<MoviesDomainModel> {
        val response = api.fetchUpcomingMovies()
        val movieData = response.body()
        return movieData?.toMoviesDomainModel() ?: emptyList()
    }

}


