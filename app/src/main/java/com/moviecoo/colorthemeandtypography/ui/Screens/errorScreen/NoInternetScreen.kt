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
import coil.compose.rememberAsyncImagePainter
import java.time.format.TextStyle

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoInternetScreen(
    onRetry: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF09274C)) // نفس الخلفية اللي في باقي الشاشة
            .padding(20.dp ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // صورة أفضل يمكن اختيار صورة دائرية أو أيقونة مناسبة
        Image(
            painter = painterResource(id = R.drawable.nointernetscreen),
            contentDescription = "No Internet",
            modifier = Modifier
                .size(180.dp)
        )

        Spacer(modifier = Modifier.height(24.dp ))

        Text(
            text = "No Internet Connection",
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Please check your connection and try again.",
            color = Color(0xFFB0B0B0),
            fontFamily = FontFamily(Font(R.font.romanesco_regular)),
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0E3E62)  // نفس لون Sign In
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
                style = androidx.compose.ui.text.TextStyle(
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
