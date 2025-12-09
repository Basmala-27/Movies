package com.moviecoo.colorthemeandtypography.common_components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.moviecoo.colorthemeandtypography.R
import androidx.compose.runtime.*
import androidx.compose.ui.draw.alpha

/**
 * ## AppNameAnimated
 * A composable function that displays the application name with an engaging entrance animation
 * (dropping down and fading in) and an optional continuous pulsing effect.
 *
 * This component is ideal for splash screens or prominent headers.
 *
 * @param name The text to display, defaulting to "Moviecoo".
 * @param modifier The modifier for this composable.
 * @param fontSize The size of the text font in sp.
 * @param width The fixed width of the Box container.
 * @param enablePulse If true, a subtle, continuous scaling animation (pulsing) is applied.
 */
@Composable
fun AppNameAnimated(
    name: String = "Moviecoo",
    modifier: Modifier = Modifier,
    fontSize: Int = 96,
    width: Dp = 218.dp,
    enablePulse: Boolean = true
) {
    // --- 1. Animation State Management ---

    // Creates an infinite transition that drives the continuous 'pulse' effect.
    val infiniteTransition = rememberInfiniteTransition(label = "PulseTransition")

    // State variable to control the one-time entrance animation (offsetY and alpha).
    var startAnimation by remember { mutableStateOf(false) }

    // --- 2. Entrance Animation (Drop-down and Fade-in) ---

    // Animates the vertical offset from -80f (off-screen top) to 0f (final position).
    val offsetY by animateFloatAsState(
        targetValue = if (startAnimation) 0f else -80f,
        animationSpec = tween(
            durationMillis = 900,
            easing = FastOutSlowInEasing
        ),
        label = "EntranceOffsetY"
    )

    // Animates the opacity (alpha) from 0f (transparent) to 1f (fully visible).
    val alpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 900,
            easing = LinearEasing
        ),
        label = "EntranceAlpha"
    )

    // Triggers the animation state change as soon as the composable enters the composition.
    LaunchedEffect(Unit) {
        startAnimation = true
    }

    // --- 3. Continuous Pulse Animation (Scaling) ---

    // Animates the scale factor infinitely between 0.97x and 1.05x if pulsing is enabled.
    val scale by infiniteTransition.animateFloat(
        initialValue = if (enablePulse) 0.97f else 1f,
        targetValue = if (enablePulse) 1.05f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1600,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse // Cycles smoothly back and forth between initial and target.
        ),
        label = "PulseScale"
    )

    // --- 4. Layout and Rendering ---

    Box(
        modifier = modifier
            .width(width)
            .wrapContentHeight()
            // Apply the pulse (scale) and entrance (alpha, offset) animations
            .scale(scale)
            .alpha(alpha)
            .offset(y = offsetY.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            fontSize = fontSize.sp,
            // Custom font definition for the app's branding
            fontFamily = FontFamily(Font(R.font.romanesco_regular)),
            fontWeight = FontWeight.Normal,
            color = Color.White,
            textAlign = TextAlign.Center,
            style = TextStyle(
                // Adds a white shadow for a glowing/3D effect
                shadow = androidx.compose.ui.graphics.Shadow(
                    color = Color.White.copy(alpha = 0.4f),
                    offset = Offset(2f, 2f),
                    blurRadius = 8f
                )
            )
        )
    }
}