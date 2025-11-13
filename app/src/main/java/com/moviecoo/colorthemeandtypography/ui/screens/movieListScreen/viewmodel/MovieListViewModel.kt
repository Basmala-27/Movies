package com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviecoo.colorthemeandtypography.data.data_source.remote.RemoteDataSource
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.provideMovieApi
import com.moviecoo.colorthemeandtypography.data.data_source.repository.MoviesRepositoryImp
import com.moviecoo.colorthemeandtypography.domain.usecase.FetchMoviesUseCase
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.MovieListUiState
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.mapper.toMoviesListUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieListViewModel(private val fetchMoviesUseCase: FetchMoviesUseCase ) : ViewModel(){
    private val _movieListStateFlow = MutableStateFlow<MovieListUiState>(MovieListUiState.Initial)
    val movieListStateFlow : StateFlow<MovieListUiState> = _movieListStateFlow.asStateFlow()

       fun requestMovies(){
         _movieListStateFlow.value = MovieListUiState.Loading(true)
         viewModelScope.launch (Dispatchers.IO) {
             try {
                 val moveListDomainModel = fetchMoviesUseCase()
                 _movieListStateFlow.value = MovieListUiState.Loading(false)
                 _movieListStateFlow.value =
                     MovieListUiState.MoviesList(moveListDomainModel.toMoviesListUiModel())

             } catch (e: Exception) {
                 _movieListStateFlow.value = MovieListUiState.Loading(false)
                 _movieListStateFlow.value = MovieListUiState.Error(e.message.toString())


             }


         }}}




