package com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.mapper

import com.moviecoo.colorthemeandtypography.data.Constants.Companion.IMAGE_ENDPOINT
import com.moviecoo.colorthemeandtypography.domain.model.MoviesDomainModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel

fun List<MoviesDomainModel>.toMoviesListUiModel(): List<MovieUiModel>{
    return this.map { results ->
        MovieUiModel(
            title = results.title,
            year = results.year,
            description = results.overview,
            genre = results.genre,
            rating = results.rating,
            image = IMAGE_ENDPOINT + results.image
        )

    }

}