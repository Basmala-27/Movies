package com.moviecoo.colorthemeandtypography.navigation

/**
 * ## NavigationCommand (Sealed Class)
 *
 * A **sealed class** used to define all distinct navigation intents that can be emitted
 * from a ViewModel (e.g., [MovieListViewModel]) to trigger a navigation action in the UI layer
 * (e.g., [AppNavHost]).
 *
 * Using a sealed class ensures that all possible commands are known at compile time,
 * enhancing safety and ensuring exhaustive handling in 'when' expressions.
 */
sealed class NavigationCommand {
    // --- 1. Main Tab Destinations (Routes without arguments) ---
    object ToMovieList : NavigationCommand() // Navigates to the main movie listing screen.
    object ToSettings : NavigationCommand() // Navigates to the settings screen.
    object ToWatchList : NavigationCommand() // Navigates to the user's watchlist screen.

    // --- 2. Destinations Triggered from Movie List Screen ---
    object ToMoodSelection : NavigationCommand() // Navigates to the screen for selecting a movie mood/genre.
    object ToRandomMovie : NavigationCommand() // Navigates to a random movie generation screen/logic.
    object ToGuessGame : NavigationCommand() // Navigates to the 'Guess the Movie' game screen.
    object ToSearch : NavigationCommand() // Navigates to the search screen (without a query).

    // --- 3. Commands with Required Arguments ---

    /**
     * Navigation command that carries a search query. Used to navigate to the search screen
     * and automatically populate the search bar or trigger results for the given [query].
     * @param query The title or search term to use on the destination screen.
     */
    data class ToSearchByTitle(val query: String) : NavigationCommand()

    /**
     * Navigation command to open the Movie Details screen.
     * @param movieId The unique identifier for the movie required by the destination route.
     */
    data class ToDetail(val movieId: Int) : NavigationCommand()

    // --- 4. Default/No Action Command ---

    /**
     * A neutral command object, often used as an initial state in LiveData/StateFlow
     * or to represent "no action" when an event is consumed.
     */
    object None : NavigationCommand()
}