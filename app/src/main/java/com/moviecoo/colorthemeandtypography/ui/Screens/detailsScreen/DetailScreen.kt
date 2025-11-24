package com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.shimmerEffect.DetailsLoadingScreen

@Composable
fun DetailsScreen(
    viewModel: MovieDetailsViewModel,
    movieId: Int
) {
    val movie by viewModel.movieDetails.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetails(movieId)
    }

    movie?.let {
        MovieDetailsUiScreen(movie = it)
    } ?: run {
        DetailsLoadingScreen()
    }
}
