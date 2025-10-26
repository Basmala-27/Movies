package com.moviecoo.colorthemeandtypography.data.data_source.remote

import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.api.MovieApi
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.MovieDataModel

class RemoteDataSource(private val movieApi: MovieApi) {
    fun fetchMovies(): MovieDataModel {

        try {
            return movieApi.fetchMovies().body() as MovieDataModel
        }catch (e: Exception){
            throw e
            }

}}