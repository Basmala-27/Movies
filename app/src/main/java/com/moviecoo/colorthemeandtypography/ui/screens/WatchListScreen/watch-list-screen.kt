package com.moviecoo.colorthemeandtypography.ui.screens.WatchListScreen


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.common_components.TopAppBar
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.NetworkModule.provideMovieApi

import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.MovieDataModel
import com.moviecoo.colorthemeandtypography.mapper.toMovieUiModel

import com.moviecoo.colorthemeandtypography.mapper.toMoviesUiModel
import com.moviecoo.colorthemeandtypography.ui.Screens.WatchListScreen.component.WatchlistStorage
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.FontSizeViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.WatchListScreen.component.MovieWatchListItem
import com.moviecoo.colorthemeandtypography.ui.theme.Primary
import com.moviecoo.colorthemeandtypography.ui.theme.Surface


@Composable
fun WatchListScreen(navController: NavController, fontSizeViewModel: FontSizeViewModel = hiltViewModel()) {
    val context = LocalContext.current
    var movies by remember { mutableStateOf(WatchlistStorage.getMovies(context)) }
    val scale = fontSizeViewModel.fontScale.value
    LaunchedEffect(navController.currentBackStackEntry) {
        movies = WatchlistStorage.getMovies(context)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                true,
                backgroundColor = Surface,
                title = R.string.watchlist,
            )
        },
        containerColor = Primary
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(movies) { movie ->
                MovieWatchListItem(
                    movieUiModel = movie.toMovieUiModel(),
                    fontSizeViewModel = fontSizeViewModel,
                    onClick = { navController.navigate("movie_details/${movie.id}") },
                    onFavoriteClick = {}
                )
            }
        }
    }
}






