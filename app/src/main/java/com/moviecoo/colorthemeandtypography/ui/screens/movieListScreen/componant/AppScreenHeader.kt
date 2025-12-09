package com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.componant

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.FontSizeViewModel

@Composable
fun AppScreenHeader(
    navController: NavController,
    onMicClick: () -> Unit,
    onHelpClick: () -> Unit,
    onSearchClick: () -> Unit,
    fontSizeViewModel: FontSizeViewModel
) {
    val hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
    val scale = fontSizeViewModel.fontScale.value
    val (greetingText, colorTint) = when (hour) {

        in 5..11 -> "Good Morning " to Color(0xFFFFC107)

        in 12..16 -> "Good Afternoon " to Color(0xFF2196F3)

        in 17..19 -> "Good Evening " to Color(0xFFFF5722)

        else -> "Good Night " to Color(0xFF00BCD4)
    }

    val fadeAnim = remember { Animatable(0f) }
    val slideAnim = remember { Animatable(-40f) }
    val scaleIcon = remember { Animatable(0.7f) }

    LaunchedEffect(Unit) {
        fadeAnim.animateTo(1f, tween(900))
        slideAnim.animateTo(0f, tween(900))
        scaleIcon.animateTo(1f, tween(600, delayMillis = 400))
    }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(2.dp*scale),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp*scale),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = greetingText,
                fontSize = 20.sp*scale,
                fontWeight = FontWeight.W700,
                color = colorTint
            )

        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp*scale)
        ) {

            IconButton(
                onClick = onSearchClick,
                modifier = Modifier.graphicsLayer(scaleX = scaleIcon.value, scaleY = scaleIcon.value)
            ) {
                Icon(Icons.Default.Search, contentDescription = "Search", tint = colorTint, modifier = Modifier.size(28.dp*scale))
            }


            IconButton(
                onClick = onMicClick,
                modifier = Modifier.graphicsLayer(scaleX = scaleIcon.value, scaleY = scaleIcon.value)
            ) {
                Icon(Icons.Default.Mic, contentDescription = "Mic", tint = colorTint, modifier = Modifier.size(28.dp*scale))
            }


            IconButton(
                onClick = onHelpClick,
                modifier = Modifier.graphicsLayer(scaleX = scaleIcon.value, scaleY = scaleIcon.value)
            ) {
                Icon(Icons.AutoMirrored.Filled.HelpOutline, contentDescription = "Help", tint = colorTint, modifier = Modifier.size(28.dp*scale))
            }
        }
    }
}