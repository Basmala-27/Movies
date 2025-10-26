package com.moviecoo.colorthemeandtypography.repository

import com.moviecoo.colorthemeandtypography.data.data_source.remote.RemoteDataSource
import com.moviecoo.colorthemeandtypography.domain.model.MoviesDomainModel
import com.moviecoo.colorthemeandtypography.domain.repository.MoviesRepository

class MoviesRepositoryImp( private val remoteDataSource: RemoteDataSource): MoviesRepository{
    override fun fetchMovies(): List<MoviesDomainModel> {
       return remoteDataSource.fetchMovies()
    }


}


