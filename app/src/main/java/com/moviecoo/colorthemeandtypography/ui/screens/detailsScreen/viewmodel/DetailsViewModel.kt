package com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.viewmodel


import androidx.lifecycle.ViewModel
import com.moviecoo.colorthemeandtypography.domain.usecase.FetchMoviesUseCase

class DetailsViewModel(private val fetchMoviesUseCase: FetchMoviesUseCase) : ViewModel(){
    fun fetchMovies(){
        fetchMoviesUseCase()

    }
}