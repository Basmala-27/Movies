package com.moviecoo.colorthemeandtypography.mapper

import coil.compose.ImagePainter
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.data.Constants.Companion.IMAGE_ENDPOINT
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.MovieDataModel
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.data.MovieDetailsUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.data.MovieDetailsDataModel

fun MovieDetailsDataModel.toMovieDetailsUiModel(): MovieDetailsUiModel {
    return MovieDetailsUiModel(
        id = id,
        title = title,
        year = release_date.take(4),
        genre = genres.joinToString { it.name },
        duration = runtime?.toString() ?: "0",
        rating = vote_average,
        overview = overview,
        image = IMAGE_ENDPOINT + poster_path,
        castImages = listOf(
            R.drawable.actor1 ,
            R.drawable.actor2 ,
            R.drawable.actor3,
            R.drawable.actor4,
            R.drawable.actor5
        )
    )
}

