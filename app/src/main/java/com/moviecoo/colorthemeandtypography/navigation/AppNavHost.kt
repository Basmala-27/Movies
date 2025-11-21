package com.moviecoo.colorthemeandtypography.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.moviecoo.colorthemeandtypography.ui.screens.splashScreen.SplashScreen

import com.moviecoo.colorthemeandtypography.common_components.MovieBottomBar

import com.moviecoo.colorthemeandtypography.ui.screens.signUpScreen.SignUpScreen

import com.moviecoo.colorthemeandtypography.mapper.toMovieUiList
import com.moviecoo.colorthemeandtypography.ui.screens.searchScreen.SearchScreen


import com.moviecoo.colorthemeandtypography.ui.screens.WatchListScreen.WatchListScreen
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.MovieListScreen
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.viewmodel.MovieListViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.seeAllScree.SeeAllScreen
import com.moviecoo.colorthemeandtypography.ui.screens.settingScreen.SettingScreen
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.SignInScreen
import com.moviecoo.colorthemeandtypography.ui.screens.signInSignUpScreen.SignInSignUpScreen




@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    val showBottomBar = currentRoute in listOf(
        "Movie_List_Screen",
        "Watch_List_Screen",
        "Setting_Screen",
        "Search_Screen"
    )

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                MovieBottomBar(
                    home = currentRoute == "Movie_List_Screen",
                    watchlist = currentRoute == "Watch_List_Screen",
                    profile = currentRoute == "Setting_Screen",
                    search = currentRoute == "Search_Screen",
                    onHomeClick = { navController.navigate("Movie_List_Screen") },
                    onWatchlistClick = { navController.navigate("Watch_List_Screen") },
                    onProfileClick = { navController.navigate("Setting_Screen") },
                    onSearchClick = {navController.navigate("search_screen")},
                    navController = navController

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
            composable("Movie_List_Screen") { MovieListScreen(
                navController = navController,
                onSeeAllClick = { title ->
                    navController.navigate("See_All_Screen/$title")
                }
              )
            }
            composable(
                "See_All_Screen/{title}",
                arguments = listOf(navArgument("title") { type = NavType.StringType })
            ) { backStackEntry ->
                val title = backStackEntry.arguments?.getString("title") ?: ""
                SeeAllScreen(title = title)
            }

            composable("Watch_List_Screen") { WatchListScreen() }
            composable("Setting_Screen") { SettingScreen() }


            composable("search_screen") {
//                val viewModel: MovieListViewModel = viewModel()
//                val moviesList = viewModel.movies.collectAsState().value


                val viewModel: MovieListViewModel = hiltViewModel()
                val moviesList by viewModel.movies.collectAsState()



                LaunchedEffect(Unit) {
                    viewModel.fetchMovies()
                }

                SearchScreen(
                    navController = navController,
                    moviesList = moviesList
                )
            }




        }
    }
}
