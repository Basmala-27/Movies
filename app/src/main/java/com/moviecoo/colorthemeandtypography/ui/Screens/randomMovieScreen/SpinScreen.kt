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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.ui.theme.Primary

@Composable

fun RandomMovieSpinScreen() {
    var isSpinning by remember { mutableStateOf(true) }

    // لازم يكون هنا في الـ Composable مش جوا Button
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
                .size(450.dp)
                .background(Primary, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_movie_reel),
                contentDescription = "Spinning Wheel",
            modifier = Modifier
                .size(6000.dp)
                .rotate(rotation)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))


    }
}
