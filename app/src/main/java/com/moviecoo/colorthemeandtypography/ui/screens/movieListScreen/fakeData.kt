package com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen

import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.data.Movies
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel

val sampleTrending = listOf(
    Movies("Quantum Paradox", 2022, 146, "Sci-Fi", 8.2, R.drawable.movie),
    Movies("Neon Nights", 2022, 113, "Action", 9.1, R.drawable.movie),
    Movies("Cosmic Voyage", 2023, 120, "Drama", 7.9, R.drawable.movie),
)

val sampleNewReleases = listOf <MovieUiModel>(
    MovieUiModel("The Matrix", "2023", "Action", "Sci-Fi", 8.7, "https://picsum.photos/200/300"),
    MovieUiModel("The Matrix", "2023", "Action", "Sci-Fi", 8.7, "https://picsum.photos/200/300"),
    MovieUiModel("The Matrix", "2023", "Action", "Sci-Fi", 8.7, "https://picsum.photos/200/300"),
)