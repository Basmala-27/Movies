package com.moviecoo.colorthemeandtypography.ui.screens.moodToMovieScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moviecoo.colorthemeandtypography.ui.screens.moodToMovie.MovieMoodViewModel

import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.moodToMovie.MoodToMovieRepository

class MovieMoodViewModelFactory(
    private val repository: MoodToMovieRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieMoodViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieMoodViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
