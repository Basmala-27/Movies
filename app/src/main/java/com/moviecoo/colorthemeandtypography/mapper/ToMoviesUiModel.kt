package com.moviecoo.colorthemeandtypography.mapper



import com.moviecoo.colorthemeandtypography.data.Constants.Companion.BASE_URL
import com.moviecoo.colorthemeandtypography.data.Constants.Companion.IMAGE_ENDPOINT
import com.moviecoo.colorthemeandtypography.data.Constants.Companion.MOVIE_ENDPOINT
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.MovieDataModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel

fun MovieDataModel.toMoviesUiModel(): List<MovieUiModel>{
    return this.results.map { results ->
        MovieUiModel(
            id = results.id,
            title = results.title,
            year = results.release_date.substring(0,4),
            description = results.overview,
            genre = results.genre_ids.toString(),
            rating = results.vote_average,
            image = IMAGE_ENDPOINT + results.poster_path
        )

    }

}

