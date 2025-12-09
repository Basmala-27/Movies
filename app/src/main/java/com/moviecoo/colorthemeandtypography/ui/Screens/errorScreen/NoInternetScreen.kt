package com.moviecoo.colorthemeandtypography.ui.Screens.errorScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
 * ## NoInternetScreen
 * A full-screen composable overlay displayed when the device has no internet connection.
 *
 * It provides a visual cue, a clear message, and a "Retry" button to prompt the user to check their network.
 *
 * @param onRetry Lambda function executed when the user taps the "Retry" button.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoInternetScreen(
    onRetry: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF09274C)) // Uses a deep blue/dark background for the overlay
            .padding(20.dp),
        verticalArrangement = Arrangement.Center, // Centers content vertically
        horizontalAlignment = Alignment.CenterHorizontally // Centers content horizontally
    ) {
        // --- 1. Visual Indicator ---
        Image(
            // Loads the local drawable asset for the no-internet indicator.
            painter = painterResource(id = R.drawable.nointernetscreen),
            contentDescription = "No Internet Connection",
            modifier = Modifier
                .size(180.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // --- 2. Primary Title Message ---
        Text(
            text = "No Internet Connection",
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        // --- 3. Secondary Descriptive Message ---
        Text(
            text = "Please check your connection and try again.",
            color = Color(0xFFB0B0B0), // Soft gray for subtitle
            fontFamily = FontFamily(Font(R.font.romanesco_regular)),
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // --- 4. Call-to-Action Button ---
        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0E3E62)
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
                    // Applies a subtle shadow/glow to the button text for emphasis.
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