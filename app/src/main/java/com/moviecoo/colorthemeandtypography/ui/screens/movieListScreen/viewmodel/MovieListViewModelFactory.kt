package com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.api.MovieApi
import com.moviecoo.colorthemeandtypography.domain.usecase.FetchMoviesUseCase


class MovieListViewModelFactory(
    private val fetchMoviesUseCase: FetchMoviesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(fetchMoviesUseCase = fetchMoviesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}