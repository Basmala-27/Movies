package com.moviecoo.colorthemeandtypography.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moviecoo.colorthemeandtypography.ui.screens.splashScreen.SplashScreen
import com.merna.setting_screen.SettingScreen
import com.moviecoo.colorthemeandtypography.common_components.MovieBottomBar
import com.moviecoo.colorthemeandtypography.ui.Screens.WatchListScreen.WatchListScreen
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.MovieListScreen
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.SignInScreen
import com.moviecoo.colorthemeandtypography.ui.screens.signInSignUpScreen.SignInSignUpScreen
import com.moviecoo.colorthemeandtypography.ui.screens.signUpScreen.SignUpScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost (navController = navController,
        modifier = modifier,
        startDestination = "splash_screen"){
        composable("splash_screen"){
            SplashScreen(
                onTimeOut = {
                   navController.navigate("Sign_In_Sign_Up_Screen")
                }
            )
        }
        composable("Sign_In_Sign_Up_Screen"){

            SignInSignUpScreen(onSignInClicked = { navController.navigate("Sign_In_Screen") },
                    onSignUpClicked = { navController.navigate("Sign_Up_Screen")})

        }

        composable("Sign_In_Screen"){
            SignInScreen { email, password ->
                navController.navigate("Movie_List_Screen")
            }
        }
        composable("Sign_Up_Screen"){
            SignUpScreen(onSignInClick = { email, password ->
                navController.navigate("Sign_In_Screen")
            }, onSignUpClick = {
                navController.navigate("Movie_List_Screen")
            })
        }
        composable("Movie_List_Screen"){
            MovieListScreen()
        }
        composable("Setting_Screen") {
            SettingScreen()
        }
        composable("Watch_List_Screen") {
            WatchListScreen()
        }
        composable ("Bottom_App_Bar") {
            MovieBottomBar(
                onHomeClick = { navController.navigate("Movie_List_Screen")},
                onWatchlistClick = { navController.navigate("Watch_List_Screen")},
                onProfileClick = { navController.navigate("Setting_Screen")})

        }
    }

}