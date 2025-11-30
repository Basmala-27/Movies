package com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviecoo.colorthemeandtypography.ui.Screens.detailsScreen.data.CastUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.data.MovieDetailsUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.repository.MovieDetailsRepository
import com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.data.UpNextMovie

import kotlinx.coroutines.launch

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MovieDetailsViewModel(private val repository: MovieDetailsRepository) : ViewModel() {

    private val _movieDetails = MutableStateFlow<MovieDetailsUiModel?>(null)
    val movieDetails: StateFlow<MovieDetailsUiModel?> get() = _movieDetails
    private val _videoKey = MutableStateFlow<String?>(null)
    val videoKey: StateFlow<String?> get() = _videoKey

    private val _videoUrl = MutableStateFlow<String?>(null)
    val videoUrl: StateFlow<String?> get() = _videoUrl

    private val _cast = MutableStateFlow<List<CastUiModel>>(emptyList())
    val cast = _cast.asStateFlow()

    private val _similarMovies = MutableStateFlow<List<UpNextMovie>>(emptyList())
    val similarMovies = _similarMovies.asStateFlow()

    fun fetchSimilarMovies(movieId: Int) {
        viewModelScope.launch {
            try {
                _similarMovies.value = repository.getSimilarMovies(movieId)
            } catch (e: Exception) {
                e.printStackTrace()
                _similarMovies.value = emptyList()
            }
        }
    }



    fun fetchCast(movieId: Int) {
        viewModelScope.launch {
            try {
                _cast.value = repository.getMovieCredits(movieId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

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


//    fun fetchCast(movieId: Int) {
//        viewModelScope.launch {
//            val response = api.getMovieCredits(movieId)
//            if (response.isSuccessful) {
//                cast.value = response.body()?.cast
//                    ?.take(10)                // أول 10 ممثلين
//                    ?.map { it.toUiModel() }  // تحويل للـ UI Model
//                    ?: emptyList()
//            }
//        }
//    }

}
