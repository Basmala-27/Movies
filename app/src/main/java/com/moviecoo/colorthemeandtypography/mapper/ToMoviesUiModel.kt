package com.moviecoo.colorthemeandtypography.mapper



import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.MovieDataModel
import com.moviecoo.colorthemeandtypography.ui.Screens.movieListScreen.model.MovieUiModel
import kotlin.String

fun MovieDataModel.toMoviesUiModel(): List<MovieUiModel>{
    return this.results.map { results ->
        MovieUiModel(
            title = results.title,
            year = results.release_date.toInt(),
            durationMin = 0,
            genre = results.genre_ids.toString(),
            rating = results.vote_average,
            image = results.poster_path.toInt()
        )

    }

}