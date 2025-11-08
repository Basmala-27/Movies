package com.moviecoo.colorthemeandtypography.ui.screens.WatchListScreen.model

data class MovieDataUiModel(
    val title: String,
    val rating: Double,
    val year: String,
    val durationMinutes: Int,
    val poster: Int
)


//year = results.release_date.substring(0,4)