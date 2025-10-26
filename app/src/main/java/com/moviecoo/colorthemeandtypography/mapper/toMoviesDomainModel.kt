package com.moviecoo.colorthemeandtypography.mapper

import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.MovieDataModel
import com.moviecoo.colorthemeandtypography.domain.model.MoviesDomainModel
import kotlin.String

fun MovieDataModel.toMoviesDomainModel(): List<MoviesDomainModel>{
    return this.articles.map { article ->
        MoviesDomainModel(
            val title: String,


        )
          }

}