package com.moviecoo.colorthemeandtypography.ui.screens.moodScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.moodToMovie.moodGenres
import com.moviecoo.colorthemeandtypography.ui.viewmodels.MoodToMovieViewModel


@Composable
fun MoodScreen(navController: NavController, viewModel: MoodToMovieViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Choose your Mood")
        moodGenres.keys.forEach { mood ->
            Button(onClick = {
                viewModel.getMoviesForMood(mood)
                navController.navigate("Movie_List_Screen")
            }) {
                Text(text = mood)
            }
        }
    }
}

