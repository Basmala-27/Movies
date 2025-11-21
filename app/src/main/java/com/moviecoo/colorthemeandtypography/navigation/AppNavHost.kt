package com.moviecoo.colorthemeandtypography.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.moviecoo.colorthemeandtypography.common_components.AnimatedBottomBar
import com.moviecoo.colorthemeandtypography.ui.screens.splashScreen.SplashScreen

import com.moviecoo.colorthemeandtypography.ui.screens.WatchListScreen.WatchListScreen
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.MovieListScreen
import com.moviecoo.colorthemeandtypography.ui.screens.settingScreen.SettingScreen
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.SignInScreen
import com.moviecoo.colorthemeandtypography.ui.screens.signInSignUpScreen.SignInSignUpScreen
import com.moviecoo.colorthemeandtypography.ui.screens.signUpScreen.SignUpScreen

@Composable
@RequiresApi(Build.VERSION_CODES.S)
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    val indexToRoute = listOf(
        "Movie_List_Screen",
        "Movie_List_Screen",
        "Movie_List_Screen",
        "Setting_Screen"
    )

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
            composable("Movie_List_Screen") { MovieListScreen() }
            composable("Watch_List_Screen") { WatchListScreen() }
            composable("Setting_Screen") { SettingScreen() }
        }
    }
}
//@Composable
//fun AppNavHost(modifier: Modifier = Modifier) {
//    val navController = rememberNavController()
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//
//
//    val showBottomBar = currentRoute in listOf(
//        "Movie_List_Screen",
//        "Watch_List_Screen",
//      "Setting_Screen"
//    )
//
//    Scaffold(
//        bottomBar = {
//            if (showBottomBar) {
//                MovieBottomBar(
//                    home = currentRoute == "Movie_List_Screen",
//                    watchlist = currentRoute == "Watch_List_Screen",
//                    profile = currentRoute == "Setting_Screen",
//                    onHomeClick = { navController.navigate("Movie_List_Screen") },
//                    onWatchlistClick = { navController.navigate("Watch_List_Screen") },
//                    onProfileClick = { navController.navigate("Setting_Screen") }
//                )
//            }
//        }
//    ) { innerPadding ->
//        val modifierWithPadding = if (showBottomBar) modifier.padding(bottom = innerPadding.calculateBottomPadding()) else modifier
//        NavHost(
//            navController = navController,
//            startDestination = "splash_screen",
//            modifier = modifierWithPadding
//        ) {
//            composable("splash_screen") {
//                SplashScreen(
//                    onTimeOut = { navController.navigate("Sign_In_Sign_Up_Screen") }
//                )
//            }
//            composable("Sign_In_Sign_Up_Screen") {
//                SignInSignUpScreen(
//                    onSignInClicked = { navController.navigate("Sign_In_Screen") },
//                    onSignUpClicked = { navController.navigate("Sign_Up_Screen") }
//                )
//            }
//            composable("Sign_In_Screen") {
//                SignInScreen(
//                    onSignInClick = { _, _ -> navController.navigate("Movie_List_Screen") },
//                    onSignUpClick = { navController.navigate("Sign_Up_Screen") }
//                )
//            }
//            composable("Sign_Up_Screen") {
//                SignUpScreen(
//                    onSignUpClick = { _, _ -> navController.navigate("Movie_List_Screen") },
//                    onSignInClick = { navController.navigate("Sign_In_Screen") }
//                )
//            }
//            composable("Movie_List_Screen") { MovieListScreen() }
//            composable("Watch_List_Screen") { WatchListScreen() }
//            composable("Setting_Screen") { SettingScreen() }
//        }
//    }
//}
