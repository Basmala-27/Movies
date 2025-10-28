package com.moviecoo.colorthemeandtypography.navigation

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moviecoo.colorthemeandtypography.MainActivity
import com.moviecoo.colorthemeandtypography.ui.screens.splashScreen.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import com.moviecoo.colorthemeandtypography.ui.screens.signInSignUpScreen.SignInSignUpScreen

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
            navController.navigate("Sign_In_Sign_Up_Screen")
        }
        composable("Sign_Up_Screen"){
            navController.navigate("Sign_In_Sign_Up_Screen")
        }
    }

}