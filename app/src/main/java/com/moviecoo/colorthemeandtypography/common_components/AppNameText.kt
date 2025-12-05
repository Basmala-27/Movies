package com.moviecoo.colorthemeandtypography.common_components


import androidx.compose.animation.animateColor
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


@Composable
fun AppNameAnimated(
    name: String = "Moviecoo",
    modifier: Modifier = Modifier,
    fontSize: Int = 96,
    width: Dp = 218.dp,
    enablePulse: Boolean = true // لو عايزة بعد الدخول يعمل حركة بسيطة
) {
    // -------- Entrance Animation --------
    val transition = rememberInfiniteTransition()

    var startAnimation by remember { mutableStateOf(false) }

    val offsetY by animateFloatAsState(
        targetValue = if (startAnimation) 0f else -80f, // ينزل من فوق
        animationSpec = tween(durationMillis = 900, easing = FastOutSlowInEasing)
    )

    val alpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f, // يبدأ شفاف
        animationSpec = tween(durationMillis = 900, easing = LinearEasing)
    )

    LaunchedEffect(Unit) {
        startAnimation = true
    }

    val scale by transition.animateFloat(
        initialValue = if (enablePulse) 0.97f else 1f,
        targetValue = if (enablePulse) 1.05f else 1f,
        animationSpec = infiniteRepeatable(
            tween(1600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = modifier
            .width(width)
            .wrapContentHeight()
            .scale(scale)
            .alpha(alpha)
            .offset(y = offsetY.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            fontSize = fontSize.sp,
            fontFamily = FontFamily(Font(R.font.romanesco_regular)),
            fontWeight = FontWeight.Normal,
            color = Color.White,
            textAlign = TextAlign.Center,
            style = TextStyle(
                shadow = androidx.compose.ui.graphics.Shadow(
                    color = Color.White.copy(alpha = 0.4f),
                    offset = Offset(2f, 2f),
                    blurRadius = 8f
                )
            )
        )
    }
}

