package com.moviecoo.colorthemeandtypography.ui.screens.searchScreen.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviecoo.colorthemeandtypography.domain.usecase.FetchMoviesUseCase
import com.moviecoo.colorthemeandtypography.mapper.toMovieUiList
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.searchScreen.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val fetchMoviesUseCase: FetchMoviesUseCase // <--- Inject default fetcher
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _searchedMovies = MutableStateFlow<List<MovieUiModel>>(emptyList())
    val searchedMovies: StateFlow<List<MovieUiModel>> = _searchedMovies
    init {
        // Automatically load default movies when the ViewModel is initialized
        searchMovies("")
    }
    fun setQueryAndSearch(query: String) {
        _searchQuery.value = query
        searchMovies(query)
    }

    private fun searchMovies(query: String) {
        viewModelScope.launch {
            val results: List<MovieUiModel> = if (query.isBlank()) {
                // --- NEW LOGIC: Load Default/Popular Movies ---
                try {
                    // Assuming FetchMoviesUseCase returns List<MoviesDomainModel>
                    fetchMoviesUseCase().toMovieUiList()
                } catch (e: Exception) {
                    emptyList()
                }
                // ---------------------------------------------
            } else {
                // Existing search logic using the repository
                searchRepository.searchMoviesByTitle(query)
            }
            _searchedMovies.value = results
        }
    }
}