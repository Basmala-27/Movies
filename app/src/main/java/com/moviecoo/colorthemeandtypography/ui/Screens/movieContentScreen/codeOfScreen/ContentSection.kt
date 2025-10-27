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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.common_components.RatingRow
import com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.data.MovieContentData
import com.moviecoo.colorthemeandtypography.ui.theme.UserAccount

@Composable
fun ContentSection(movieContentData: MovieContentData) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = movieContentData.title,
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily(Font(R.font.roboto_regular))
        )
        Spacer(modifier = Modifier.height(12.dp))
        RatingRow(movieContentData.rating)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "${movieContentData.year} | ${movieContentData.genre} | ${movieContentData.duration}",
            color = UserAccount,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = movieContentData.description,
            color = UserAccount,
            fontSize = 18.sp,
            lineHeight = 22.sp
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "cast",
            color = Color.White,
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
            fontWeight = FontWeight.Bold
        )
        LazyRow(modifier = Modifier.padding(top = 12.dp)) {
            items(movieContentData.cast) { actorRes ->
                Image(
                    painter = painterResource(actorRes),
                    contentDescription = "Cast member",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(1.dp))
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Up Next",
            color = Color.White,
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.roboto_bold)),
            fontWeight = FontWeight.Bold
        )
        Box(
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
        ) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(end = 50.dp)
            ) {
                items(movieContentData.upNext) { movie ->
                    Column(
                        modifier = Modifier.padding(end = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(movie.posterRes),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(110.dp)
                                .height(165.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = movie.title1,
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 14.sp
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(Color.White.copy(alpha = 0.3f), Color.White.copy(alpha = 0.1f)),
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    contentDescription = "Scroll next",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}