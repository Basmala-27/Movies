package com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.codeOfScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.common_components.RatingRow
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.FontSizeViewModel

import com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.data.MovieContentData
import com.moviecoo.colorthemeandtypography.ui.theme.UserAccount

@Composable
fun ContentSection(
    title: String,
    rating: Double,
    year: String,
    genre: String,
    duration: String,
    description: String,
    staticData: MovieContentData, // Supplies the static cast/upnext lists
    fontSizeViewModel: FontSizeViewModel
) {
    val scale = fontSizeViewModel.fontScale.value
    Column(modifier = Modifier.padding(16.dp * scale)) {

        // 💡 DYNAMIC DATA
        Text(
            text = title,
            color = Color.White,
            fontSize = 32.sp * scale,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily(Font(R.font.roboto_regular))
        )
        Spacer(modifier = Modifier.height(12.dp * scale))

        // Rating
        // NOTE: Assuming RatingRow takes a Float or Double
        RatingRow(rating)
        Spacer(modifier = Modifier.height(8.dp * scale))

        // Movie Info
        // 💡 DYNAMIC DATA
        Text(
            text = "$year | $genre | $duration",
            color = UserAccount,
            fontSize = 18.sp * scale
        )
        Spacer(modifier = Modifier.height(16.dp * scale))

        // Description
        // 💡 DYNAMIC DATA
        Text(
            text = description,
            color = UserAccount,
            fontSize = 18.sp * scale,
            lineHeight = 22.sp * scale
        )
        Spacer(modifier = Modifier.height(24.dp * scale))

        // Cast Title
        Text(
            text = "Cast",
            color = Color.White,
            fontSize = 24.sp * scale,
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
            fontWeight = FontWeight.Bold
        )

        // Cast List (STATIC DATA)
        LazyRow(modifier = Modifier.padding(top = 12.dp * scale)) {
            items(staticData.cast) { actorRes -> // Use staticData.cast
                Image(
                    painter = painterResource(actorRes),
                    contentDescription = "Cast member",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(35.dp * scale)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(1.dp * scale))
            }
        }
        Spacer(modifier = Modifier.height(24.dp * scale))

        // Up Next Title
        Text(
            text = "Up Next",
            color = Color.White,
            fontSize = 24.sp * scale,
            fontFamily = FontFamily(Font(R.font.roboto_bold)),
            fontWeight = FontWeight.Bold
        )

        // Up Next List (STATIC DATA)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp * scale)
        ) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(end = 50.dp * scale)
            ) {
                items(staticData.upNext) { movie -> // Use staticData.upNext
                    Column(
                        modifier = Modifier.padding(end = 12.dp * scale),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(movie.posterRes),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(110.dp * scale)
                                .height(165.dp * scale)
                                .clip(RoundedCornerShape(8.dp * scale))
                        )
                        Spacer(modifier = Modifier.height(8.dp * scale))
                        Text(
                            text = movie.title1,
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 14.sp * scale
                        )
                    }
                }
            }
            // Scroll Icon (omitted for brevity, but stays here)
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(40.dp * scale)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.3f),
                                Color.White.copy(alpha = 0.1f)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    contentDescription = "Scroll next",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp * scale)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp * scale))
    }
}