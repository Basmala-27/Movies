package com.moviecoo.colorthemeandtypography.mapper

import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.MovieDataModel
import com.moviecoo.colorthemeandtypography.domain.model.DetailsDomainModel
import com.moviecoo.colorthemeandtypography.domain.model.MoviesDomainModel

fun MovieDataModel.toDetailsDomainModel(): List<DetailsDomainModel>{
    return this.results.map { results ->
        DetailsDomainModel(
            title = results.title,
            year = results.release_date.toInt(),
            durationMin = 0,
            genre = results.genre_ids.toString(),
            rating = results.vote_average,
            image = results.poster_path.toInt(),
            cast =results
        )

    }

}