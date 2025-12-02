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
import coil.compose.rememberAsyncImagePainter
import com.google.common.math.Quantiles.scale
import com.moviecoo.colorthemeandtypography.R

@Composable
fun PoorConnectionScreen(
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // نفس حجم الصورة ومصدرها زي NoInternetScreen
        Image(
            painter = painterResource(id = R.drawable.poorinternetscreen), // صورة ضعيفة النت
            contentDescription = "Poor Internet",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // نفس الخط واللون والحجم زي NoInternetScreen
        Text(
            "Weak Internet Connection",
            color = Color.White,
            fontSize = 22.sp
        )
        Text(
            "Your internet is slow. Please check your connection and try again.",
            fontFamily = FontFamily(Font(R.font.romanesco_regular)),
            color = Color.Gray,
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

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
