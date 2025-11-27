package com.moviecoo.colorthemeandtypography.ui.screens.randomMovieScreen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.FontSizeViewModel
import com.moviecoo.colorthemeandtypography.ui.theme.Primary

@Composable
fun RandomMovieSpinScreen(
    fontSizeViewModel: FontSizeViewModel
) {
    val scale = fontSizeViewModel.fontScale.value

    var isSpinning by remember { mutableStateOf(true) }

    LaunchedEffect(true) {
        kotlinx.coroutines.delay(3)
    }

    val rotation by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = if (isSpinning) 360f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 8500, easing = LinearEasing)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(450.dp * scale)
                .background(Primary, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_movie_reel),
                contentDescription = "Spinning Wheel",
                modifier = Modifier
                    .size(400.dp * scale) // الحجم متناسب مع scale
                    .rotate(rotation)
            )
        }

        Spacer(modifier = Modifier.height(40.dp * scale))
    }
}
