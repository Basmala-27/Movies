package com.moviecoo.colorthemeandtypography.navigation


import SignUpScreen
import android.os.Build
import androidx.annotation.OptIn
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.moviecoo.colorthemeandtypography.common_components.AnimatedBottomBar
import com.moviecoo.colorthemeandtypography.ui.screens.splashScreen.SplashScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.livedata.observeAsState
import com.moviecoo.colorthemeandtypography.ui.screens.searchScreen.viewModel.SearchViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.geminiAssist.AssistantScreen
import com.moviecoo.colorthemeandtypography.ui.screens.geminiAssist.viewModel.AssistantViewModel
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.NetworkModule.provideMovieApi
import com.moviecoo.colorthemeandtypography.mapper.toMovieUiList
import com.moviecoo.colorthemeandtypography.ui.Screens.favoriteScreen.FavoriteScreen
import com.moviecoo.colorthemeandtypography.ui.Screens.onBoardingScreen.OnboardingScreen
import com.moviecoo.colorthemeandtypography.ui.Screens.settingScreen.ReportProblemScreen
import com.moviecoo.colorthemeandtypography.ui.Screens.settingScreen.TermsScreenWithCard
import com.moviecoo.colorthemeandtypography.ui.Screens.signInScreen.signInScreen.SignInScreen
import com.moviecoo.colorthemeandtypography.ui.Screens.splashScreen.SplashDestination
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.FontSizeViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.WatchListScreen.WatchListScreen
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.DetailsScreen
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.repository.MovieDetailsRepository
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.MovieDetailsViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.factory.MovieDetailsViewModelFactory
import com.moviecoo.colorthemeandtypography.ui.screens.generalChat.ChatScreen
import com.moviecoo.colorthemeandtypography.ui.screens.guessTheMovieScreen.GuessMovieScreen
import com.moviecoo.colorthemeandtypography.ui.screens.moodToMovieScreen.MoodToMovieScreen
import com.moviecoo.colorthemeandtypography.ui.screens.moodToMovieScreen.moodToMovieViweModel.MoodSelectionScreen
import com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.codeOfScreen.MovieContentScreen
import com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.data.sampleMovie
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.MovieListScreen
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.moodToMovie.MoodToMovieRepository
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.viewmodel.MovieListViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.searchScreen.SearchScreen
import com.moviecoo.colorthemeandtypography.ui.screens.seeAllScree.SeeAllScreen
import com.moviecoo.colorthemeandtypography.ui.screens.settingScreen.SettingScreen
import com.moviecoo.colorthemeandtypography.ui.screens.signInSignUpScreen.SignInSignUpScreen
import dagger.hilt.android.UnstableApi

/**
 * ## AppNavHost
 * The main entry point for Jetpack Compose navigation in the application.
 *
 * It manages the navigation stack, defines all screen routes, and conditionally displays
 * the custom [AnimatedBottomBar] using a [Scaffold]. It also handles navigation commands
 * triggered from voice assistants integrated into the ViewModels.
 *
 * @param modifier The modifier for this composable.
 * @param fontSizeViewModel ViewModel for managing global font scaling preferences.
 * @param onLaunchSpeechRecognizer Lambda to start speech recognition for MovieList commands.
 * @param onLaunchSearchVoice Lambda to start voice input for Search screen queries.
 * @param onLaunchAssistantVoice Lambda to start voice input for the Assistant screen.
 */
@OptIn(UnstableApi::class)
@Composable
@RequiresApi(Build.VERSION_CODES.S)
fun AppNavHost(
    modifier: Modifier = Modifier,
    fontSizeViewModel: FontSizeViewModel,
    onLaunchSpeechRecognizer: (vm: MovieListViewModel) -> Unit,
    onLaunchSearchVoice: (vm: SearchViewModel) -> Unit,
    onLaunchAssistantVoice: (vm: AssistantViewModel) -> Unit // Handler for voice input on the Assistant screen
) {
    // NavController is created and remembered, managing the navigation stack.
    val navController = rememberNavController()

    // Observes the current back stack entry to determine the active route.
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // ViewModel instances used across multiple screens or for global logic (e.g., voice commands).
    val movieListViewModel: MovieListViewModel = hiltViewModel()

    // --- VOICE NAVIGATION: Observe Navigation Commands ---
    // Observes navigation commands emitted by the MovieListViewModel (likely from a voice input processing).
    val navigationEvent by movieListViewModel.navigationEvent.observeAsState()

    LaunchedEffect(navigationEvent) {
        navigationEvent?.getContentIfNotHandled()?.let { command ->
            when (command) {
                NavigationCommand.ToMovieList -> navController.navigate("Movie_List_Screen") {
                    // Clears the back stack up to and including this screen to prevent loops.
                    popUpTo("Movie_List_Screen") { inclusive = true }
                }
                NavigationCommand.ToSettings -> navController.navigate("Setting_Screen")
                NavigationCommand.ToWatchList -> navController.navigate("Watch_List_Screen")
                NavigationCommand.ToMoodSelection -> navController.navigate("moodSelection")
                NavigationCommand.ToRandomMovie -> navController.navigate("randomMovie")
                NavigationCommand.ToGuessGame -> navController.navigate("guessTheMovie")
                NavigationCommand.ToSearch -> navController.navigate("search_screen")

                is NavigationCommand.ToDetail -> navController.navigate("movie_details/${command.movieId}")

                // Handles navigation to the search screen with a pre-filled query from voice input.
                is NavigationCommand.ToSearchByTitle -> {
                    val encodedQuery = java.net.URLEncoder.encode(command.query, "UTF-8")
                    // Note: The route should include the encoded query if using the argument.
                    // Current implementation navigates to search screen without passing the query correctly in the route template.
                    navController.navigate("search_screen?query=$encodedQuery") // Fixed route construction
                }
                NavigationCommand.None -> { /* No action needed */ }
            }
        }
    }

    // Determine if the bottom bar should be visible based on the current route.
    val showBottomBar = currentRoute in listOf(
        "Movie_List_Screen",
        "chat_screen", // Added chat_screen to the bottom bar routes
        "Favorite_Screen",
        "Watch_List_Screen",
        "Setting_Screen"
    )

    // Maps the current route to the index required by the AnimatedBottomBar.
    val selectedIndex = when (currentRoute) {
        "Movie_List_Screen" -> 0
        "chat_screen"       -> 1
        "Favorite_Screen"   -> 2
        "Watch_List_Screen" -> 3
        "Setting_Screen"    -> 4
        else -> 0
    }

    Scaffold(
        // Conditional Bottom Navigation Bar
        bottomBar = {
            if (showBottomBar) {
                AnimatedBottomBar(
                    feedItemCount = 0, // Placeholder for dynamic badge count
                    selectedIndex = selectedIndex,
                    onItemSelected = { index ->
                        // Maps the selected index back to a navigation route.
                        val route = when(index) {
                            0 -> "Movie_List_Screen"
                            1 -> "chat_screen"
                            2 -> "Favorite_Screen"
                            3 -> "Watch_List_Screen"
                            4 -> "Setting_Screen"
                            else -> "Movie_List_Screen"
                        }

                        if (route != currentRoute) {
                            // Standard navigation pattern for bottom bar items
                            navController.navigate(route) {
                                launchSingleTop = true // Prevents multiple copies of the destination on the stack
                                restoreState = true    // Restores state when reselecting a previously visited item
                            }
                        }
                    },
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    ) { innerPadding ->
        // Apply bottom padding to the NavHost to prevent content from drawing under the bottom bar.
        val modifierWithPadding = if (showBottomBar) modifier.padding(bottom = innerPadding.calculateBottomPadding()) else modifier

        NavHost(
            navController = navController,
            startDestination = "splash_screen", // Initial destination when the app starts.
            modifier = modifierWithPadding
        ) {
            // --- Splash Screen ---
            composable("splash_screen") {
                SplashScreen { destination ->
                    // Handles navigation based on the Splash Screen's determination (e.g., first run, logged in).
                    val route = when(destination) {
                        SplashDestination.SIGN_IN -> "sign_in_sign_up_screen"
                        SplashDestination.MOVIE_LIST -> "movie_list_screen"
                        SplashDestination.ONBOARDING -> "onboarding_screen"
                    }
                    navController.navigate(route) {
                        // Clears the splash screen from the back stack completely.
                        popUpTo("splash_screen") { inclusive = true }
                    }
                }
            }

            // --- Authentication Flow ---
            composable("Sign_In_Sign_Up_Screen") {
                SignInSignUpScreen(
                    onSignInClicked = { navController.navigate("Sign_In_Screen") },
                    onSignUpClicked = { navController.navigate("Sign_Up_Screen") }
                )
            }
            composable("Sign_In_Screen") {
                SignInScreen(
                    // Navigate to the main list upon successful sign-in.
                    onSignInClick = { _, _ -> navController.navigate("Movie_List_Screen") },
                    onSignUpClick = { navController.navigate("Sign_Up_Screen") }
                )
            }
            composable("Sign_Up_Screen") {
                SignUpScreen(
                    // Navigate to the main list upon successful sign-up.
                    onSignUpClick = { _, _ -> navController.navigate("Movie_List_Screen") },
                    onSignInClick = { navController.navigate("Sign_In_Screen") }
                )
            }

            // --- Main Bottom Bar Screens ---
            composable("chat_screen") {
                ChatScreen(navController)
            }

            composable("Movie_List_Screen") {
                MovieListScreen(
                    navController = navController,
                    viewModel = movieListViewModel, // Inject ViewModel for content and voice commands
                    onVoiceCommand = onLaunchSpeechRecognizer, // Lambda to launch system voice recognition
                    onFeaturedClick = { navController.navigate("moodSelection") },
                    onSeeAllClick = { title -> navController.navigate("See_All_Screen/$title") },
                    onRandomClick = { navController.navigate("randomMovie") },
                    onGuessClick = { navController.navigate("guessTheMovie") } ,
                    onMovieClick = { movie -> navController.navigate("movie_details/${movie.id}") },
                    fontSizeViewModel = fontSizeViewModel ,
                    onAssistantClick = { navController.navigate("assistant_screen") },
                )
            }
            composable("Watch_List_Screen") { WatchListScreen(navController = navController,
                fontSizeViewModel = fontSizeViewModel) }

            composable("Setting_Screen") {
                SettingScreen(fontSizeViewModel = fontSizeViewModel, navController = navController)
            }

            composable("Favorite_Screen") {
                FavoriteScreen(
                    navController = navController,
                    fontSizeViewModel = fontSizeViewModel
                )
            }

            // --- Specialized/Feature Screens ---
            composable("guessTheMovie") {
                GuessMovieScreen(viewModel = viewModel(),fontSizeViewModel = fontSizeViewModel )
            }

            composable("moodSelection") {
                MoodSelectionScreen(onMoodSelected = { genreId ->
                    navController.navigate("moodToMovie/$genreId")
                },
                    fontSizeViewModel = fontSizeViewModel)
            }

            // Screen that displays movies based on a selected mood/genre.
            composable("moodToMovie/{genreId}") { backStackEntry ->
                val genreId = backStackEntry.arguments?.getString("genreId")
                val repository = MoodToMovieRepository(provideMovieApi())
                MoodToMovieScreen(
                    viewModel = repository,
                    genreId = genreId,
                    fontSizeViewModel = fontSizeViewModel,
                    navController = navController
                )
            }

            // Screen for random movie selection (likely reuses the MoodToMovieScreen logic).
            composable("randomMovie") {
                val repository = MoodToMovieRepository(provideMovieApi())
                MoodToMovieScreen(
                    viewModel = repository,
                    fontSizeViewModel = fontSizeViewModel,
                    navController = navController
                )
            }

            // Screen to display a full list of movies for a category (e.g., "Trending").
            composable(
                "See_All_Screen/{title}",
                arguments = listOf(navArgument("title") { type = NavType.StringType })
            ) { backStackEntry ->
                val title = backStackEntry.arguments?.getString("title") ?: ""
                SeeAllScreen(
                    title = title,
                    fontSizeViewModel = fontSizeViewModel,
                    onMovieClick = { movie -> navController.navigate("movie_details/${movie.id}") }
                )
            }

            // Detail screen for a specific movie, requiring a movieId argument.
            composable(
                "movie_details/{movieId}",
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
                // Manual ViewModel setup using a Factory due to repository dependency.
                val repository = MovieDetailsRepository(provideMovieApi())
                val viewModel: MovieDetailsViewModel = viewModel(
                    factory = MovieDetailsViewModelFactory(repository)
                )

                DetailsScreen(
                    movieId = movieId,
                    viewModel = viewModel,
                    fontSizeViewModel = fontSizeViewModel,
                    navController = navController
                )
            }

            // Screen for movie content playback.
            composable(
                "movie_content/{movieId}",
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
                // Reuses the movie details repository and factory setup.
                val repository = MovieDetailsRepository(provideMovieApi())
                val viewModel: MovieDetailsViewModel = viewModel(
                    factory = MovieDetailsViewModelFactory(repository)
                )

                MovieContentScreen(
                    movieId = movieId,
                    viewModel = viewModel,
                    fontSizeViewModel = fontSizeViewModel,
                    navController = navController
                )
            }

            // Search Screen, optionally accepting a voice-input query via argument.
            composable(
                "search_screen?query={query}",
                arguments = listOf(navArgument("query") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                })
            ) { backStackEntry ->
                val voiceQuery = backStackEntry.arguments?.getString("query")

                SearchScreen(
                    navController = navController,
                    fontSizeViewModel = fontSizeViewModel,
                    modifier = Modifier.fillMaxSize(),
                    voiceQuery = voiceQuery, // Pass the voice query to the screen
                    onVoiceSearchClick = onLaunchSearchVoice // Pass handler to launch voice input
                )
            }

            // Gemini Assistant Screen with voice input capability.
            composable("assistant_screen") {
                AssistantScreen(
                    onVoiceInputClicked = onLaunchAssistantVoice
                )
            }

            // Onboarding Screen (first-time user experience).
            composable("onboarding_screen") {
                OnboardingScreen(navController = navController)
            }

            // --- Settings Sub-Screens ---
            composable("report_problem_screen") {
                ReportProblemScreen(fontSizeViewModel = fontSizeViewModel)
            }
            composable("dummy_terms_screen") {
                // The ViewModel is re-injected here, which is redundant if already available in the scope.
                val localFontSizeViewModel: FontSizeViewModel = hiltViewModel()
                TermsScreenWithCard(fontSizeViewModel = localFontSizeViewModel)
            }
        }
    }
}