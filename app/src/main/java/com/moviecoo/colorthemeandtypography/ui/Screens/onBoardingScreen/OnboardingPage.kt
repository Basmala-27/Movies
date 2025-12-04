package com.moviecoo.colorthemeandtypography.ui.Screens.onBoardingScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.ui.theme.GradientBackground
import com.moviecoo.colorthemeandtypography.ui.theme.OnPrimary

data class OnboardingPage(
    val imageRes: Int,
    val title: String,
    val description: String
)

@Composable
fun OnboardingScreen(
    navController: NavController,
    fontScale: Float = 1f
) {
    val pages = listOf(
        OnboardingPage(
            imageRes = R.drawable.onboarding1,
            title = "Welcome to MovieCoo",
            description = "Discover your favorite movies easily!"
        ),
        OnboardingPage(
            imageRes = R.drawable.onboarding2,
            title = "Personalized Recommendations",
            description = "Get movies suggestions based on your mood."
        ),
        OnboardingPage(
            imageRes = R.drawable.onboarding3,
            title = "play with us",
            description = "guess movies and playing with your friends."
        )
    )

    var currentPage by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = GradientBackground)
    ) {
        // الصورة كاملة بدون قص
        Image(
            painter = painterResource(id = pages[currentPage].imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f), // 60% من الشاشة
            contentScale = ContentScale.Fit // ← الصورة كاملة
        )

        // Overlay خفيف لتوضيح النصوص
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .background(Color.Black.copy(alpha = 0.2f))
        )

        // Texts and Buttons
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = pages[currentPage].title,
                fontSize = 32.sp * fontScale,
                color = OnPrimary,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.romanesco_regular))
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = pages[currentPage].description,
                fontSize = 18.sp * fontScale,
                color = OnPrimary.copy(alpha = 0.9f),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.staatliches_regular))
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Pagination Dots
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                pages.forEachIndexed { index, _ ->
                    Box(
                        modifier = Modifier
                            .size(if (index == currentPage) 12.dp else 8.dp)
                            .padding(4.dp)
                            .background(
                                color = if (index == currentPage) OnPrimary else OnPrimary.copy(alpha = 0.4f),
                                shape = RoundedCornerShape(50)
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Next / Start Button
            Button(
                onClick = {
                    if (currentPage < pages.size - 1) {
                        currentPage++
                    } else {
                        navController.navigate("Sign_In_Sign_Up_Screen") {
                            popUpTo("onboarding_screen") { inclusive = true }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEC255A)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = if (currentPage < pages.size - 1) "Next" else "Start",
                    fontSize = 18.sp * fontScale,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Skip Button
            AnimatedVisibility(visible = currentPage < pages.size - 1) {
                Text(
                    text = "Skip",
                    fontSize = 16.sp * fontScale,
                    color = Color.White.copy(alpha = 0.7f),
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            navController.navigate("Sign_In_Sign_Up_Screen") {
                                popUpTo("onboarding_screen") { inclusive = true }
                            }
                        }
                )
            }
        }
    }
}
