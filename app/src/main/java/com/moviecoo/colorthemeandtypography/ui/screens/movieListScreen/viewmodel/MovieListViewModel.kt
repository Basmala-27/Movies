package com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.MovieDataModel
import com.moviecoo.colorthemeandtypography.domain.model.MoviesDomainModel
import com.moviecoo.colorthemeandtypography.domain.usecase.FetchMoviesUseCase
import com.moviecoo.colorthemeandtypography.mapper.toMovieUiList
import com.moviecoo.colorthemeandtypography.navigation.Event
import com.moviecoo.colorthemeandtypography.navigation.NavigationCommand
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.searchScreen.repository.SearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



@HiltViewModel
class MovieListViewModel @Inject constructor (
    private val fetchMoviesUseCase: FetchMoviesUseCase,
    private val searchRepository: SearchRepository // <-- Use the new SearchRepository
) : ViewModel() {
    private val _navigationEvent = MutableLiveData<Event<NavigationCommand>>()
    val navigationEvent: LiveData<Event<NavigationCommand>> = _navigationEvent

    fun processVoiceCommand(command: String) {
        val lowerCaseCommand = command.lowercase().trim()

        // Use a variable to hold the title if found in the command
        val movieTitleToSearch: String? = when {
            // Case 1: If the user says "Show details for Avatar"
            lowerCaseCommand.startsWith("show details for") ->
                lowerCaseCommand.substringAfter("show details for").trim()

            // Case 2: If the user says "Search for Avatar"
            lowerCaseCommand.startsWith("search for") ->
                lowerCaseCommand.substringAfter("search for").trim()

            else -> null
        }

        // --- REFACTORED 'WHEN' AS A STATEMENT ---
        when {
            // Synchronous Navigation
            lowerCaseCommand.contains("go to settings") || lowerCaseCommand.contains("open settings") -> {
                _navigationEvent.value = Event(NavigationCommand.ToSettings)
            }
            lowerCaseCommand.contains("watch list") || lowerCaseCommand.contains("my list") -> {
                _navigationEvent.value = Event(NavigationCommand.ToWatchList)
            }
            lowerCaseCommand.contains("guess the movie") -> {
                _navigationEvent.value = Event(NavigationCommand.ToGuessGame)
            }
            lowerCaseCommand.contains("random movie") -> {
                _navigationEvent.value = Event(NavigationCommand.ToRandomMovie)
            }
            lowerCaseCommand.contains("movie to mood") -> {
                _navigationEvent.value = Event(NavigationCommand.ToMoodSelection)
            }
            movieTitleToSearch != null && movieTitleToSearch.isNotEmpty() -> {
                searchAndNavigateToDetail(movieTitleToSearch)
            }

            // Asynchronous Navigation (Detail Lookup)
            lowerCaseCommand.startsWith("show details for") -> {
                val movieTitle = lowerCaseCommand.substringAfter("show details for").trim()
                if (movieTitle.isNotEmpty()) {
                    searchAndNavigateToDetail(movieTitle)
                }

            }

            // Navigation with Argument (Immediate Search)
            lowerCaseCommand.startsWith("search for") -> {
                val movieTitle = lowerCaseCommand.substringAfter("search for").trim()
                if (movieTitle.isNotEmpty()) {
                    _navigationEvent.value = Event(NavigationCommand.ToSearchByTitle(movieTitle))
                }
            }

            else -> {
                // If no command matches, do nothing.
                // We don't emit NavigationCommand.None to avoid unnecessary event firing.
            }
        }
    }
    // -----------------------------------


    private val _movies = MutableStateFlow<List<MovieUiModel>>(emptyList())
    val movies: StateFlow<List<MovieUiModel>> = _movies

    fun fetchMovies(){
        viewModelScope.launch {
            val domainList: List<MoviesDomainModel> = fetchMoviesUseCase()
            _movies.value = domainList.toMovieUiList()
        }
    }

    private fun searchAndNavigateToDetail(title: String) {
        viewModelScope.launch {
            // This function is designed to search and go to the detail screen on success.
            val movieId = searchRepository.getMovieIdByTitle(title)

            if (movieId != null && movieId != 0) {
                // SUCCESS: Navigate to the detail screen
                _navigationEvent.value = Event(NavigationCommand.ToDetail(movieId))
            } else {
                // FAILURE/NO MATCH: Navigate to the search screen with the query pre-filled
                // This gives the user the search results page to manually select the movie.
                _navigationEvent.value = Event(NavigationCommand.ToSearchByTitle(title))
            }
        }
    }
}


