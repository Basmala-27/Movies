package com.moviecoo.colorthemeandtypography.mapper


import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.data.MovieDetailsUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel

fun MovieDetailsUiModel.toMovieUiModel(): MovieUiModel {
    return MovieUiModel(
        id = this.id,
        title = this.title,
        year = this.year,
        description = this.overview,
        genre = this.genre,
        rating = this.rating,
        image = this.image
    )
}