package com.moviecoo.colorthemeandtypography.ui.Screens.detailsScreen.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.moviecoo.colorthemeandtypography.domain.usecase.FetchMoviesUseCase

class DetailsViewModel(private val fetchMoviesUseCase: FetchMoviesUseCase) : ViewModel(){
    fun fetchMovies(){
        fetchMoviesUseCase()

    }
}