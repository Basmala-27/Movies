package com.moviecoo.colorthemeandtypography


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.moviecoo.colorthemeandtypography.navigation.AppNavHost
import com.moviecoo.colorthemeandtypography.ui.screens.splashScreen.SplashScreen
import com.moviecoo.colorthemeandtypography.ui.theme.ColorThemeandTypographyTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  enableEdgeToEdge()
        setContent {
            ColorThemeandTypographyTheme {
//                SplashScreen(
//                    onTimeOut = {
//                        val intent = Intent(this, MainActivity::class.java)
//                        startActivity(intent)
//                        finish()
//
//                    }
//                )
                AppNavHost()
            }
        }
    }
}




