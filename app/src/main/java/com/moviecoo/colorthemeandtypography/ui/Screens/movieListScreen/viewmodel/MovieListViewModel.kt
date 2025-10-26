package com.moviecoo.colorthemeandtypography.ui.Screens.movieListScreen.viewmodel

import androidx.lifecycle.ViewModel
import com.moviecoo.colorthemeandtypography.domain.usecase.FetchMoviesUseCase

class MovieListViewModel(private val fetchMoviesUseCase: FetchMoviesUseCase) : ViewModel(){
    fun fetchMovies(){
        fetchMoviesUseCase()

    }
}