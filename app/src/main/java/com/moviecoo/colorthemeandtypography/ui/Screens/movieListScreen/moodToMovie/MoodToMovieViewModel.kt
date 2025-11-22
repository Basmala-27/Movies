// MovieViewModel.kt
package com.moviecoo.colorthemeandtypography.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.moodToMovie.MoodToMovieRepository
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.moodToMovie.moodGenres
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoodToMovieViewModel(private val repository: MoodToMovieRepository) : ViewModel() {

    private val _movies = MutableStateFlow<List<MovieUiModel>>(emptyList())
    val movies: StateFlow<List<MovieUiModel>> = _movies

    private var selectedMood: String? = null

    fun getMoviesForMood(mood: String) {
        selectedMood = mood
        val genreIds = moodGenres[mood]?.joinToString(",") ?: ""
        viewModelScope.launch {
            try {

                _movies.value = repository.getMoviesByMood(genreIds)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getSelectedMood() = selectedMood
}
