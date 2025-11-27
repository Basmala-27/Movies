package com.moviecoo.colorthemeandtypography


import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import com.moviecoo.colorthemeandtypography.helpers.PermissionHelper
import com.moviecoo.colorthemeandtypography.navigation.AppNavHost
import com.moviecoo.colorthemeandtypography.services.ServiceStarter
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.FontSizeViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.LocalFontScale
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




