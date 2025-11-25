package com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.data.MovieDetailsUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repository: MovieDetailsRepository) : ViewModel() {

    private val _movieDetails = MutableStateFlow<MovieDetailsUiModel?>(null)
    val movieDetails: StateFlow<MovieDetailsUiModel?> get() = _movieDetails

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                val details = repository.getMovieDetails(movieId)
                _movieDetails.value = details
            } catch (e: Exception) {
                e.printStackTrace()
                _movieDetails.value = null
            }
        }
    }
}
