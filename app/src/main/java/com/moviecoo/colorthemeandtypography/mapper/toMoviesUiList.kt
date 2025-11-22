package com.moviecoo.colorthemeandtypography.mapper

import com.moviecoo.colorthemeandtypography.data.Constants.Companion.BASE_URL
import com.moviecoo.colorthemeandtypography.domain.model.MoviesDomainModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel


fun List<MoviesDomainModel>.toMovieUiList(): List<MovieUiModel> {
    return this.map { domain ->
        MovieUiModel(
            title = domain.title,
            year = domain.year,
            description = domain.description ?: "",
            genre = domain.genre,
            rating = domain.rating,
            image = BASE_URL + domain.image
        )
    }
}