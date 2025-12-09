package com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen

import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.FontSizeViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.shimmerEffect.DetailsLoadingScreen
import androidx.compose.ui.Modifier // Added for completeness, although not used here.

/**
 * ## DetailsScreen
 * The top-level Composable screen that handles data fetching and state management
 * for displaying comprehensive movie details.
 *
 * It observes the details from the [MovieDetailsViewModel] and conditionally renders
 * the final UI ([MovieDetailsUiScreen]) or a loading placeholder ([DetailsLoadingScreen]).
 *
 * @param viewModel The ViewModel responsible for fetching and holding movie details data.
 * @param movieId The unique ID of the movie to fetch details for.
 * @param fontSizeViewModel ViewModel for managing global font scaling preferences.
 * @param navController The NavController used for navigation actions from this screen.
 */
@Composable
fun DetailsScreen(
    viewModel: MovieDetailsViewModel,
    movieId: Int,
    fontSizeViewModel: FontSizeViewModel,
    navController: NavController,
) {
    // Retrieves the current font scale for accessibility adjustments in child composables.
    val scale = fontSizeViewModel.fontScale.value

    // Observes the movie details StateFlow from the ViewModel. 'movie' will be null initially.
    val movie by viewModel.movieDetails.collectAsState()

    // --- Data Fetching and Lifecycle Management ---
    // LaunchedEffect is used to trigger asynchronous data loading when the screen first
    // enters the composition OR if the movieId changes (which it shouldn't in this case).
    LaunchedEffect(movieId) {
        // Trigger the ViewModel to fetch all necessary data for the screen.
        viewModel.fetchMovieDetails(movieId)
        viewModel.fetchCast(movieId)        // Fetches cast list data
        viewModel.fetchSimilarMovies(movieId) // Fetches related movie recommendations
    }

    // --- Conditional Rendering ---
    // If 'movie' has been successfully loaded (is not null), render the detailed UI.
    movie?.let {
        MovieDetailsUiScreen(
            movie = it,
            fontSizeViewModel = fontSizeViewModel,
            navController = navController
        )
    } ?: run {
        // If 'movie' is null (initial state or still loading), show the loading placeholder.
        DetailsLoadingScreen()
    }
}