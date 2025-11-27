package com.moviecoo.colorthemeandtypography


import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.media3.common.Timeline
import com.moviecoo.colorthemeandtypography.helpers.PermissionHelper
import com.moviecoo.colorthemeandtypography.navigation.AppNavHost
import com.moviecoo.colorthemeandtypography.services.ServiceStarter
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.viewmodel.MovieListViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.searchScreen.viewModel.SearchViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.FontSizeViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.LocalFontScale
import com.moviecoo.colorthemeandtypography.ui.theme.ColorThemeandTypographyTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val fontSizeViewModel: FontSizeViewModel by viewModels()

    // --- VOICE NAVIGATION ADDITIONS ---
    private var searchVoiceViewModel: SearchViewModel? = null
    private var voiceNavViewModel: MovieListViewModel? = null

    private val speechRecognizerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val spokenText: String? = result.data?.getStringArrayListExtra(
                RecognizerIntent.EXTRA_RESULTS)?.get(0)


            spokenText?.let { command ->
                voiceNavViewModel?.processVoiceCommand(command)
            }
        }
    }
    private val searchRecognizerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val spokenText: String? = result.data?.getStringArrayListExtra(
                RecognizerIntent.EXTRA_RESULTS)?.get(0)

            // Pass the transcribed text to the stored SearchViewModel
            spokenText?.let { command ->
                searchVoiceViewModel?.setQueryAndSearch(command)
            }
        }
    }
    private fun handleSearchVoiceLaunch(vm: SearchViewModel) {
        // Store the VM instance so the launcher can access it later
        searchVoiceViewModel = vm

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak your search query...")
        }
        searchRecognizerLauncher.launch(intent)
    }
    private fun handleVoiceCommandLaunch(vm: MovieListViewModel) {

        voiceNavViewModel = vm

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak a movie command...")
        }
        speechRecognizerLauncher.launch(intent)
    }



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

        setContent {
            val fontScale by fontSizeViewModel.fontScale
            CompositionLocalProvider(
                LocalFontScale provides fontScale
            ) {
                ColorThemeandTypographyTheme {
                    window.insetsController?.let { controller ->
                        controller.hide(WindowInsets.Type.systemBars())
                        controller.systemBarsBehavior =
                            WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                    }

                    AppNavHost(
                        fontSizeViewModel = fontSizeViewModel,
                        onLaunchSpeechRecognizer = ::handleVoiceCommandLaunch, // Main Nav
                        onLaunchSearchVoice = ::handleSearchVoiceLaunch       // NEW: Search Voice
                    )
                }
            }
        }
    }
}

