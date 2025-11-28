package com.moviecoo.colorthemeandtypography.navigation

sealed class NavigationCommand {
    // Main Tab Destinations
    object ToMovieList : NavigationCommand() // Movie_List_Screen
    object ToSettings : NavigationCommand() // Setting_Screen
    object ToWatchList : NavigationCommand() // Watch_List_Screen

    // Destinations triggered from Movie List Screen
    object ToMoodSelection : NavigationCommand() // moodSelection
    object ToRandomMovie : NavigationCommand() // randomMovie
    object ToGuessGame : NavigationCommand() // guessTheMovie
    object ToSearch : NavigationCommand() // search_screen

    // NEW: Command that triggers a search and carries the search query (title)
    data class ToSearchByTitle(val query: String) : NavigationCommand()

    // Detail Screen (requires an argument)
    data class ToDetail(val movieId: Int) : NavigationCommand()

    object None : NavigationCommand()

}