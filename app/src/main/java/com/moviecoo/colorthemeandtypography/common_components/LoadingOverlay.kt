package com.moviecoo.colorthemeandtypography.common_components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


/**
 * ## LoadingOverlay
 * A fullscreen composable that displays a modal loading indicator, typically used
 * to block user interaction while a background task (e.g., a network call) is in progress.
 *
 * It uses a semi-transparent black background to visually distinguish the loading state.
 *
 * @param overlayAlpha The transparency level of the black background (0.0f to 1.0f).
 * @param indicatorStroke The thickness of the loading spinner's stroke line in Dp.
 * @param indicatorSize The size (width and height) of the CircularProgressIndicator in Dp.
 */
@Composable
fun LoadingOverlay(
    overlayAlpha: Float = 0.5f,
    indicatorStroke: Float = 3f,
    indicatorSize: Int = 64 // dp
) {
    Box(
        modifier = Modifier
            // 1. Ensures the Box covers the entire available screen space.
            .fillMaxSize()
            // 2. Applies a semi-transparent black color to block touch events
            //    and visually indicate a modal/loading state.
            .background(Color.Black.copy(alpha = overlayAlpha)),
        // 3. Centers the child content (the spinner) within the Box.
        contentAlignment = Alignment.Center
    ) {
        // Displays the continuous, indeterminate loading spinner.
        CircularProgressIndicator(
            color = Color.White, // Typically a contrasting color for visibility
            strokeWidth = indicatorStroke.dp,
            modifier = Modifier.size(indicatorSize.dp)
        )
    }
}