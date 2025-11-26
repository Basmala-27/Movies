package com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.codeOfScreen

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.moviecoo.colorthemeandtypography.ui.Screens.signInScreen.fontSizeViewModel.FontSizeViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.MovieDetailsViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.data.MovieContentData
import com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.data.sampleMovie
import com.moviecoo.colorthemeandtypography.ui.theme.GradientBackground


import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.data.MovieDetailsUiModel // Assuming you have this model

@Composable
fun MovieContentScreen(
    movieContentData: MovieContentData = sampleMovie,
    movieId: Int,
    viewModel: MovieDetailsViewModel,
    fontSizeViewModel: FontSizeViewModel
) {
    val scale = fontSizeViewModel.fontScale.value
    val movieDetails by viewModel.movieDetails.collectAsState()
    val videoKey by viewModel.videoKey.collectAsState()



    LaunchedEffect(Unit) {
        viewModel.fetchMovieDetails(movieId)
        viewModel.fetchTrailer(movieId)
    }

    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val isReady = movieDetails != null

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(GradientBackground)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        IconButton(
            modifier = Modifier.padding(top = 10.dp * scale),
            onClick = { backDispatcher?.onBackPressed() }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(24.dp * scale)
            )
        }
        if ( videoKey != null) {
            YouTubePlayerComposable(
                youtubeVideoKey = videoKey!!,
                modifier = Modifier.height(220.dp * scale)
            )
        } else {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp * scale)
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (videoKey == null) "Loading Trailer..."  else "Error Loading Trailer",
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp * scale))


        if (!isReady) {

            Text(text = "Loading movie details...", color = Color.White)
        } else {
            val details = movieDetails!!


            ContentSection(
                title = details.title,
                rating = details.rating,
                year = details.year,
                genre = details.genre,
                duration = details.duration + "min",
                description = details.overview,
                staticData = movieContentData,
                fontSizeViewModel = fontSizeViewModel
            )

        }
    }
}