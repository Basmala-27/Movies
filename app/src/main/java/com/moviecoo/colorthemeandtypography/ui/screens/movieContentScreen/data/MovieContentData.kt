package com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.data

import androidx.annotation.DrawableRes

data class MovieContentData(
    val id: Int,
val title: String,
val rating: Double,
val year: Int,
val genre: String,
val duration: String,
val description: String,
@DrawableRes val videoThumbnail: Int,
val cast: List<Int>,
val upNext: List<UpNextMovie>
)


