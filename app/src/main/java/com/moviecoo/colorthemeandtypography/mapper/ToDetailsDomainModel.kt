package com.moviecoo.colorthemeandtypography.mapper

import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.MovieDataModel
import com.moviecoo.colorthemeandtypography.domain.model.DetailsDomainModel
import com.moviecoo.colorthemeandtypography.domain.model.MoviesDomainModel

fun MovieDataModel.toDetailsDomainModel(): List<DetailsDomainModel>{
    return this.results.map { results ->
        DetailsDomainModel(
            id = results.id,
            title = results.title,
            year = results.release_date.substring(0,4),
            duration = "0",
            genre = results.genre_ids.toString(),
            rating = results.vote_average,
            image = results.poster_path,
            overview = results.overview,
            castImages = emptyList()
        )

    }




}

