package com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen

import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel

sealed class MovieListUiState {
    data object Initial : MovieListUiState()
    data class Loading (val isLoading : Boolean = true) : MovieListUiState()
    data class MoviesList(val movies: List<MovieUiModel>)  : MovieListUiState()
    data class Error(val message: String) : MovieListUiState()

}