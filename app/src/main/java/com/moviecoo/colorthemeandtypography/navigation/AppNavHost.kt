package com.moviecoo.colorthemeandtypography.navigation

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

import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.NetworkModule.provideMovieApi
import com.moviecoo.colorthemeandtypography.mapper.toMovieUiList
import com.moviecoo.colorthemeandtypography.ui.Screens.signInScreen.fontSizeViewModel.FontSizeViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.WatchListScreen.WatchListScreen
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.DetailsScreen
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.repository.MovieDetailsRepository
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.MovieDetailsViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.factory.MovieDetailsViewModelFactory
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
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.SignInScreen
import com.moviecoo.colorthemeandtypography.ui.screens.signInSignUpScreen.SignInSignUpScreen
import com.moviecoo.colorthemeandtypography.ui.screens.signUpScreen.SignUpScreen
import dagger.hilt.android.UnstableApi

@OptIn(UnstableApi::class)
@Composable
@RequiresApi(Build.VERSION_CODES.S)
fun AppNavHost(modifier: Modifier = Modifier, fontSizeViewModel: FontSizeViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    val showBottomBar = currentRoute in listOf(
        "Movie_List_Screen",
        "Watch_List_Screen",
      "Setting_Screen"
    )

    val selectedIndex = when (currentRoute) {
        "Movie_List_Screen" -> 0
        "Watch_List_Screen" -> 3
        "Setting_Screen" -> 4
        else -> 0
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                AnimatedBottomBar(
                    feedItemCount = 0,
                    selectedIndex = selectedIndex,
                    onItemSelected = { index ->
                        val route = when(index) {
                            0 -> "Movie_List_Screen"
                            1 -> "Movie_List_Screen"
                            2 -> "Movie_List_Screen"
                            3 -> "Watch_List_Screen"
                            4 -> "Setting_Screen"
                            else -> "Movie_List_Screen"
                        }
                        if (route != currentRoute) {
                            navController.navigate(route) {
                                launchSingleTop = true
                                restoreState = true

                            }
                        }
                    },
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    ) { innerPadding ->
        val modifierWithPadding = if (showBottomBar) modifier.padding(bottom = innerPadding.calculateBottomPadding()) else modifier
        NavHost(
            navController = navController,
            startDestination = "splash_screen",
            modifier = modifierWithPadding
        ) {
            composable("splash_screen") {
                SplashScreen(
                    onTimeOut = { navController.navigate("Sign_In_Sign_Up_Screen") }
                )
            }
            composable("Sign_In_Sign_Up_Screen") {
                SignInSignUpScreen(
                    onSignInClicked = { navController.navigate("Sign_In_Screen") },
                    onSignUpClicked = { navController.navigate("Sign_Up_Screen") }
                )
            }
            composable("Sign_In_Screen") {
                SignInScreen(
                    onSignInClick = { _, _ -> navController.navigate("Movie_List_Screen") },
                    onSignUpClick = { navController.navigate("Sign_Up_Screen") }
                )
            }
            composable("Sign_Up_Screen") {
                SignUpScreen(
                    onSignUpClick = { _, _ -> navController.navigate("Movie_List_Screen") },
                    onSignInClick = { navController.navigate("Sign_In_Screen") }
                )
            }
            composable("Movie_List_Screen") {
                MovieListScreen(
                    navController = navController,
                    onFeaturedClick = { navController.navigate("moodSelection") },
                    onSeeAllClick = { title -> navController.navigate("See_All_Screen/$title")
                    },
                    onRandomClick = { navController.navigate("randomMovie")
                    },
                    onGuessClick = { navController.navigate("guessTheMovie") } ,
                    onMovieClick = { movie -> navController.navigate("movie_details/${movie.id}") },
                    fontSizeViewModel = fontSizeViewModel
                )
            }
            composable("guessTheMovie") {
                GuessMovieScreen(viewModel = viewModel(),fontSizeViewModel = fontSizeViewModel )
            }

            composable("moodSelection") {
                MoodSelectionScreen(onMoodSelected = { genreId ->
                    navController.navigate("moodToMovie/$genreId")
                },
                    fontSizeViewModel = fontSizeViewModel)
            }

            composable("moodToMovie/{genreId}") { backStackEntry ->
                val genreId = backStackEntry.arguments?.getString("genreId")
                val repository = MoodToMovieRepository(provideMovieApi())
                MoodToMovieScreen(viewModel = repository, genreId = genreId,
                    fontSizeViewModel = fontSizeViewModel )
            }

            composable("randomMovie") {
                val repository = MoodToMovieRepository(provideMovieApi())
                MoodToMovieScreen(viewModel = repository, fontSizeViewModel = fontSizeViewModel )// تمرير الحجم المشترك)
            }
            composable(
                "See_All_Screen/{title}",
                arguments = listOf(navArgument("title") { type = NavType.StringType })
            ) { backStackEntry ->
                val title = backStackEntry.arguments?.getString("title") ?: ""
                SeeAllScreen(title = title , fontSizeViewModel = fontSizeViewModel,
                    onMovieClick = { movie -> navController.navigate("movie_details/${movie.id}") })
            }

            composable("Watch_List_Screen") { WatchListScreen(fontSizeViewModel = fontSizeViewModel) }

            composable("Setting_Screen") {
                SettingScreen(fontSizeViewModel = fontSizeViewModel)
            }
            composable(
                "movie_details/{movieId}",
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
                val repository = MovieDetailsRepository(provideMovieApi())
                val viewModel: MovieDetailsViewModel = viewModel(
                    factory = MovieDetailsViewModelFactory(repository)
                )

                DetailsScreen(movieId = movieId, viewModel = viewModel,fontSizeViewModel = fontSizeViewModel, navController = navController)
            }




            composable("search_screen") {
//              val viewModel: MovieListViewModel = viewModel()
//              val moviesList = viewModel.movies.collectAsState().value


                val viewModel: MovieListViewModel = hiltViewModel()

                SearchScreen(
                    navController = navController,
                    fontSizeViewModel = fontSizeViewModel
                )


            }
            composable(
                "movie_content/{movieId}",
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
                val repository = MovieDetailsRepository(provideMovieApi())
                val viewModel: MovieDetailsViewModel = viewModel(
                    factory = MovieDetailsViewModelFactory(repository)
                )

                MovieContentScreen(
                    // لو عندك بيانات جاهزة
                    movieId = movieId,
                    viewModel = viewModel,
                    fontSizeViewModel = fontSizeViewModel
                )
            }

        }
    }
}
