package com.moviecoo.colorthemeandtypography.ui.screens.splashScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moviecoo.colorthemeandtypography.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onTimeOut: ()-> Unit

    ) {
    var isVisile by remember{ mutableStateOf(true) }
    LaunchedEffect(Unit) {
        delay(3000)
        isVisile = false
        onTimeOut()
    }

    AnimatedVisibility(
        isVisile,


    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            Image(
                painter = painterResource(R.drawable.splashscreen),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop

            )

            val glowShadow = Shadow(
                color = Color.White.copy(alpha = 0.8f),
                offset = Offset(0f, 0f),
                blurRadius = 10f
            )


            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val moviecooFontFamily = FontFamily(Font(R.font.romanesco_regular))
                val staatlichesFontFamily = FontFamily(Font(R.font.staatliches_regular))

                Text(
                    text = "Moviecoo",
                    fontSize = 96.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
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








@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun PreviewSplashScreen() {
    SplashScreen(onTomeOut = {})

}