// File: YouTubePlayerComposable.kt

package com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.codeOfScreen // Adjust package as needed

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback // THIS IS THE CRUCIAL IMPORT
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YouTubePlayerComposable(
    youtubeVideoKey: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    // 1. Initialize the YouTubePlayerView
    val youtubePlayerView = remember {
        YouTubePlayerView(context).apply {
            // Attach the native Android View to the Compose lifecycle
            lifecycleOwner.lifecycle.addObserver(this)
        }
    }

    AndroidView(
        factory = { youtubePlayerView },
        modifier = modifier.fillMaxWidth(),
        update = { view ->

            // 2. Call the function and provide the correct callback type
            //    This fixes the 'Argument type mismatch' error.
            view.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
                override fun onYouTubePlayer(youtubePlayer: YouTubePlayer) {
                    // 3. Load the video using the raw key
                    youtubePlayer.cueVideo(youtubeVideoKey, 0f)
                }
            })
        }
    )

    // 4. Clean up the player when the Composable is destroyed
    DisposableEffect(key1 = youtubePlayerView) {
        onDispose {
            youtubePlayerView.release()
        }
    }
}