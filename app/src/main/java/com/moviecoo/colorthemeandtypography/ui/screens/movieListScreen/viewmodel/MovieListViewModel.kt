package com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.MovieDataModel
import com.moviecoo.colorthemeandtypography.domain.model.MoviesDomainModel
import com.moviecoo.colorthemeandtypography.domain.usecase.FetchMoviesUseCase
import com.moviecoo.colorthemeandtypography.mapper.toMovieUiList
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



@HiltViewModel
class MovieListViewModel @Inject constructor (private val fetchMoviesUseCase: FetchMoviesUseCase) : ViewModel(){



    private val _movies = MutableStateFlow<List<MovieUiModel>>(emptyList())
    val movies: StateFlow<List<MovieUiModel>> = _movies



    fun fetchMovies(){
//        fetchMoviesUseCase()


        viewModelScope.launch {
            val domainList: List<MoviesDomainModel> = fetchMoviesUseCase()
            _movies.value = domainList.toMovieUiList()
        }

//    private val _movies = MutableStateFlow<List<MoviesDomainModel>>(emptyList())
//    val movies: StateFlow<List<MoviesDomainModel>> = _movies
//
//    init {
//        viewModelScope.launch {
//            _movies.value = fetchMoviesUseCase()
//        }
//}
    }}