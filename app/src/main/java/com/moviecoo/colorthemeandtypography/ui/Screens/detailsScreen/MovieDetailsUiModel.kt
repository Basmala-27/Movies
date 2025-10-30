package com.moviecoo.colorthemeandtypography.ui.Screens.detailsScreen



data class MovieDetailsUiModel(
    val title: String,
    val year: String,
    val genre: String,
    val duration: String,
    val rating: Double,
    val overview: String,
    val image: String,
    val castImages: List<Int> = emptyList()
)
