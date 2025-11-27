package com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen

import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.FontSizeViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.shimmerEffect.DetailsLoadingScreen

@Composable
fun DetailsScreen(
    viewModel: MovieDetailsViewModel,
    movieId: Int, fontSizeViewModel: FontSizeViewModel
    , navController: NavController,
) {
    val scale = fontSizeViewModel.fontScale.value
    val movie by viewModel.movieDetails.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetails(movieId)
    }

    movie?.let {
        MovieDetailsUiScreen(movie = it, fontSizeViewModel = fontSizeViewModel , navController)
    } ?: run {
        DetailsLoadingScreen()
    }
}
