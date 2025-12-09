package com.moviecoo.colorthemeandtypography.ui.Screens.errorScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moviecoo.colorthemeandtypography.R
import androidx.compose.ui.text.TextStyle // Import the correct TextStyle

/**
 * ## PoorConnectionScreen
 * A full-screen composable overlay displayed when the device has an active connection
 * but the estimated bandwidth is too low for optimal app performance.
 *
 * It provides a clear notification and a "Retry" button. This is often less severe
 * than a total disconnection.
 *
 * @param onRetry Lambda function executed when the user taps the "Retry" button.
 */
@Composable
fun PoorConnectionScreen(
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            // Assume the background is handled by the parent or is transparent to show the app background.
            .padding(20.dp),
        verticalArrangement = Arrangement.Center, // Centers content vertically
        horizontalAlignment = Alignment.CenterHorizontally // Centers content horizontally
    ) {
        // --- 1. Visual Indicator ---
        Image(
            // Loads the local drawable asset for the poor connection indicator.
            painter = painterResource(id = R.drawable.poorinternetscreen),
            contentDescription = "Poor Internet Connection",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // --- 2. Primary Title Message ---
        Text(
            "Weak Internet Connection",
            color = Color.White,
            fontSize = 22.sp
        )
        // --- 3. Secondary Descriptive Message ---
        Text(
            "Your internet is slow. Please check your connection and try again.",
            fontFamily = FontFamily(Font(R.font.romanesco_regular)),
            color = Color.Gray,
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        // --- 4. Call-to-Action Button ---
        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0E3E62) // Uses the primary theme color
            ),
            shape = RoundedCornerShape(13.dp),
            modifier = Modifier
                .width(351.dp)
                .height(57.dp)
        ) {
            Text(
                text = "Retry",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily(Font(R.font.inter_medium)),
                style = TextStyle(
                    // Applies a subtle shadow/glow to the button text.
                    shadow = Shadow(
                        color = Color.White,
                        offset = Offset(0f, 0f),
                        blurRadius = 6f
                    )
                )
            )
        }
    }
}