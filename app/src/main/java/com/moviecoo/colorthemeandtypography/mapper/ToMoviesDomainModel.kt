package com.moviecoo.colorthemeandtypography.mapper

import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.MovieDataModel
import com.moviecoo.colorthemeandtypography.domain.model.MoviesDomainModel

fun MovieDataModel.toMoviesDomainModel(): List<MoviesDomainModel>{
    return this.results.map { results ->
       MoviesDomainModel(
           title = results.title,
           year = results.release_date.substring(0,4),
           durationMin = 0,
           genre = results.genre_ids.toString(),
           rating = results.vote_average,
           image = results.poster_path,
           description = results.overview
       )

          }

}