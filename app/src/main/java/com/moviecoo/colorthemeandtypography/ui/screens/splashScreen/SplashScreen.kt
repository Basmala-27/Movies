package com.moviecoo.colorthemeandtypography.ui.screens.splashScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.ui.Screens.signInScreen.dataStoreManager.DataStoreManager
import com.moviecoo.colorthemeandtypography.ui.Screens.signInScreen.launchViewModel.LaunchViewModel
import com.moviecoo.colorthemeandtypography.ui.Screens.splashScreen.SplashDestination
import com.moviecoo.colorthemeandtypography.ui.theme.GradientBackground
import com.moviecoo.colorthemeandtypography.ui.theme.OnPrimary
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigate: (SplashDestination) -> Unit
) {
    val context = LocalContext.current
    val viewModel: LaunchViewModel = viewModel()
    val launchCount by viewModel.launchCount.collectAsState()
    var isVisible by remember { mutableStateOf(false) }
    var scale by remember { mutableStateOf(0.5f) }
    var glowAlpha by remember { mutableStateOf(0.5f) }

    LaunchedEffect(Unit) {
        viewModel.loadLaunchCount(context)
    }
    LaunchedEffect(launchCount) {
        if (launchCount >= 0) {
            isVisible = true
            scale = 1.2f
            glowAlpha = 1f
            delay(300)
            scale = 0.9f
            delay(200)
            scale = 1f
            delay(1000)
            delay(1000)
            isVisible = false
            delay(500)
            val destination = when {
                launchCount == 0 -> SplashDestination.ONBOARDING
                launchCount >= 15 -> SplashDestination.SIGN_IN
                else -> SplashDestination.MOVIE_LIST
            }

            onNavigate(destination)
            if (destination == SplashDestination.SIGN_IN) {
                DataStoreManager.resetLaunchCount(context)
            } else {
                viewModel.increase(context)
            }
        }
    }
    val animatedScale by animateFloatAsState(
        targetValue = scale,
        animationSpec = tween(durationMillis = 300)
    )

    val animatedGlow by animateColorAsState(
        targetValue = Color.White.copy(alpha = glowAlpha),
        animationSpec = tween(durationMillis = 300)
    )

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(animationSpec = tween(500)),
        exit = fadeOut(animationSpec = tween(500))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = GradientBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .scale(animatedScale),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val moviecooFontFamily = FontFamily(Font(R.font.romanesco_regular))
                val staatlichesFontFamily = FontFamily(Font(R.font.staatliches_regular))

                val glowShadow = Shadow(
                    color = animatedGlow,
                    offset = Offset(0f, 0f),
                    blurRadius = 15f
                )

                Text(
                    text = "Moviecoo",
                    fontSize = 96.sp,
                    color = OnPrimary,
                    fontFamily = moviecooFontFamily,
                    style = TextStyle(shadow = glowShadow)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "WATCH AND FIND MOVIE THAT\nBRING YOUR MODE BACK",
                    fontSize = 24.sp,
                    color = Color.LightGray,
                    fontFamily = staatlichesFontFamily,
                    style = TextStyle(textAlign = TextAlign.Center)
                )
            }
        }
    }
}
