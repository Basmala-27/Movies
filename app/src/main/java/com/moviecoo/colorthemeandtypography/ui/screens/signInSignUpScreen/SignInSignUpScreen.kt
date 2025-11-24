package com.moviecoo.colorthemeandtypography.ui.screens.signInSignUpScreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moviecoo.colorthemeandtypography.R

import com.moviecoo.colorthemeandtypography.ui.theme.ColorThemeandTypographyTheme
import com.moviecoo.colorthemeandtypography.ui.theme.Primary


@Composable
fun SignInSignUpScreen(
    onSignInClicked: () -> Unit ,
    onSignUpClicked: () -> Unit
) {
    ColorThemeandTypographyTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.signinsignupscreenbackgound),
                contentDescription = "Sign in Sign up background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp, vertical = 64.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center// Distributes content vertically
            ) {

                Spacer(modifier = Modifier.height(200.dp))

                val romanescoFontFamily = FontFamily(Font(R.font.romanesco_regular))
                Text(
                    text = "Moviecoo",
                    color = Color.White,
                    fontSize = 96.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = romanescoFontFamily,

                    modifier = Modifier.padding(bottom = 100.dp)
                )


                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()

                ) {

                    Spacer(modifier = Modifier.weight(1f))


                    Button(

                        onClick = { onSignInClicked() },
                        colors = ButtonDefaults.buttonColors(containerColor = Primary),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp), shape = RoundedCornerShape(13.dp)
                    ) {
                        Text("Sign In", fontSize = 18.sp, color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(16.dp))


                    Text(
                        text = "or",
                        color = Color.LightGray,
                        fontSize = 20.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))


                    Button(
                        onClick = { onSignUpClicked() },
                        colors = ButtonDefaults.buttonColors(containerColor = Primary),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp), shape = RoundedCornerShape(13.dp)
                    ) {
                        Text("Sign Up", fontSize = 18.sp, color = Color.White)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ColorThemeandTypographyTheme {
        SignInSignUpScreen(onSignInClicked = {}, onSignUpClicked = {})
    }
}

