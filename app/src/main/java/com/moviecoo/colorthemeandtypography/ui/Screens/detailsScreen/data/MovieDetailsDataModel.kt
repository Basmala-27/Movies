package com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.data

data class MovieDetailsDataModel(
    val id: Int,
    val title: String,
    val release_date: String,
    val runtime: Int?,
    val vote_average: Double,
    val overview: String,
    val poster_path: String,
    val genres: List<Genre>
) {
    data class Genre(
        val id: Int,
        val name: String
    )
}
