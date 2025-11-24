package com.moviecoo.colorthemeandtypography.ui.screens.moodToMovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.moodToMovie.MoodToMovieRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieMoodViewModel(private val repository: MoodToMovieRepository) : ViewModel() {

    private val _movies = MutableStateFlow<List<MovieUiModel>>(emptyList())
    val movies: StateFlow<List<MovieUiModel>> = _movies

    private val _selectedMovie = MutableStateFlow<MovieUiModel?>(null)
    val selectedMovie: StateFlow<MovieUiModel?> = _selectedMovie

    fun getMoviesByMood(genreIds: String) {
        viewModelScope.launch {
            try {
                _movies.value = repository.getMoviesByMood(genreIds)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun selectMovie(movie: MovieUiModel?) {
        _selectedMovie.value = movie
    }

}
