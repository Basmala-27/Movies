package com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.moviecoo.colorthemeandtypography.ui.Screens.signInScreen.fontSizeViewModel.FontSizeViewModel
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
