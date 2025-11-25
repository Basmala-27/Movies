package com.moviecoo.colorthemeandtypography.ui.screens.seeAllScree


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.common_components.TopAppBar
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.NetworkModule.provideMovieApi

import com.moviecoo.colorthemeandtypography.ui.screens.WatchListScreen.model.MovieDataUiModel
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.MovieDataModel

import com.moviecoo.colorthemeandtypography.mapper.toMoviesUiModel
import com.moviecoo.colorthemeandtypography.ui.Screens.signInScreen.fontSizeViewModel.FontSizeViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.seeAllScree.component.MovieSeeAllItem
import com.moviecoo.colorthemeandtypography.ui.theme.Primary
import com.moviecoo.colorthemeandtypography.ui.theme.Surface


@Composable
fun SeeAllScreen(
    title: String = "",
    fontSizeViewModel: FontSizeViewModel,
    onMovieClick: (MovieUiModel) -> Unit = {}
) {
    val movieListState = remember {
        mutableStateOf<MovieDataModel?>(null)
    }

    LaunchedEffect(Unit) {
        when (title) {
            "Trending Now" -> {
                val response = provideMovieApi().fetchMovies()
                movieListState.value = response.body() as MovieDataModel
            }

            "New Releases" -> {
                val response = provideMovieApi().fetchNowPlayingMovies()
                movieListState.value = response.body() as MovieDataModel
            }

            "Upcoming" -> {
                val response = provideMovieApi().fetchUpcomingMovies()
                movieListState.value = response.body() as MovieDataModel
            }

            "Top Rated" -> {
                val response = provideMovieApi().fetchTopRatingMovies()
                movieListState.value = response.body() as MovieDataModel
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                true,
                backgroundColor = Surface,
                title = R.string.allMovies,
            )
        },
        containerColor = Primary
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            movieListState.value?.let {
                items(it.toMoviesUiModel()) { movie ->
                    MovieSeeAllItem(
                        movieUiModel = movie,
                        fontSizeViewModel = fontSizeViewModel,
                        onMovieClick = { onMovieClick(movie) }
                    )
                }
            }
        }
    }
}
