package com.moviecoo.colorthemeandtypography


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moviecoo.colorthemeandtypography.common_components.AnimatedBottomBar
import com.moviecoo.colorthemeandtypography.helpers.PermissionHelper
import com.moviecoo.colorthemeandtypography.navigation.AppNavHost
import com.moviecoo.colorthemeandtypography.services.ServiceStarter
import com.moviecoo.colorthemeandtypography.ui.Screens.signInScreen.fontSizeViewModel.FontSizeViewModel
import com.moviecoo.colorthemeandtypography.ui.Screens.signInScreen.fontSizeViewModel.LocalFontScale
import com.moviecoo.colorthemeandtypography.ui.screens.randomMovieScreen.RandomMovieSpinScreen
import com.moviecoo.colorthemeandtypography.ui.screens.splashScreen.SplashScreen
import com.moviecoo.colorthemeandtypography.ui.theme.ColorThemeandTypographyTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    private val fontSizeViewModel: FontSizeViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WebView(this).destroy()
        val permissionHelper = PermissionHelper(this) {
            ServiceStarter.startBackgroundServices(this)
            ServiceStarter.startUIAndNotificationServices(this)
        }
        permissionHelper.initLauncher()
        permissionHelper.checkAndRequestPermissions()

        //  enableEdgeToEdge()

        setContent {
            val fontScale by fontSizeViewModel.fontScale

            CompositionLocalProvider(
                LocalFontScale provides fontScale
            ) {
                ColorThemeandTypographyTheme {
//                SplashScreen(
//                    onTimeOut = {
//                        val intent = Intent(this, MainActivity::class.java)
//                        startActivity(intent)
//                        finish()
//
//                    }
//                )
                    window.insetsController?.let { controller ->
                        controller.hide(WindowInsets.Type.systemBars())
                        controller.systemBarsBehavior =
                            WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                    }
                    AppNavHost(fontSizeViewModel = fontSizeViewModel)
                }
            }
        }
    }
}




