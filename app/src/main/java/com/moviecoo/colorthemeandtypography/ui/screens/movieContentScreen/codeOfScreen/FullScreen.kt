package com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.codeOfScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.NetworkModule.provideMovieApi
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.MovieDataModel
import com.moviecoo.colorthemeandtypography.ui.Screens.signInScreen.fontSizeViewModel.FontSizeViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.data.MovieContentData
import com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.data.sampleMovie
import com.moviecoo.colorthemeandtypography.ui.theme.GradientBackground

@Composable
fun MovieContentScreen(movieContentData: MovieContentData,fontSizeViewModel: FontSizeViewModel) {


    val movieListState = remember {
        mutableStateOf<MovieDataModel?>(null)
    }
    LaunchedEffect(Unit) {
        val response = provideMovieApi().fetchNowPlayingMovies()
        movieListState.value = response.body() as MovieDataModel
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .background(GradientBackground)
            .verticalScroll(rememberScrollState())

    ) {
        VideoPlayerSection(thumbnailRes = movieContentData.videoThumbnail,fontSizeViewModel)
        ContentSection(movieContentData = movieContentData,fontSizeViewModel)
    }
}
