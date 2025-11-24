package com.moviecoo.colorthemeandtypography.ui.screens.guessTheMovieScreen.data

data class GuessMovie(
    val movieId: Int,
    val title: String,
    val poster: String,
    val options: List<String>,
    val correctAnswer: String
)