package com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.api.MovieApi
import com.moviecoo.colorthemeandtypography.mapper.toMovieDetailsUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.data.MovieDetailsUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.repository.MovieDetailsRepository

import kotlinx.coroutines.launch

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repository: MovieDetailsRepository) : ViewModel() {

    private val _movieDetails = MutableStateFlow<MovieDetailsUiModel?>(null)
    val movieDetails: StateFlow<MovieDetailsUiModel?> get() = _movieDetails
    private val _videoKey = MutableStateFlow<String?>(null)
    val videoKey: StateFlow<String?> get() = _videoKey

    private val _videoUrl = MutableStateFlow<String?>(null)
    val videoUrl: StateFlow<String?> get() = _videoUrl

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

    fun fetchTrailer(movieId: Int) {
        viewModelScope.launch {
            try {
                // repository.getMovieTrailer returns the key
                val key = repository.getMovieTrailer(movieId)



                if (key != null) {
                    _videoKey.value = key // Store the raw key
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _videoKey.value = null
            }

        }
    }
}
