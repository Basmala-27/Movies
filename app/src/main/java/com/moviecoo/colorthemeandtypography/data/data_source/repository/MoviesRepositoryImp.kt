package com.moviecoo.colorthemeandtypography.data.data_source.repository

import com.moviecoo.colorthemeandtypography.data.data_source.remote.RemoteDataSource
import com.moviecoo.colorthemeandtypography.domain.model.MoviesDomainModel
import com.moviecoo.colorthemeandtypography.domain.repository.MoviesRepository
import com.moviecoo.colorthemeandtypography.mapper.toMoviesDomainModel

class MoviesRepositoryImp (
    private val remoteDataSource: RemoteDataSource
) : MoviesRepository {
    override suspend fun fetchMovies(): List<MoviesDomainModel> {
        return remoteDataSource.fetchMovies().toMoviesDomainModel()
    }


}