package com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.common_components.TopAppBar
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.FontSizeViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.data.MovieDetailsUiModel


@Composable
fun MovieDetailsUiScreen(movie: MovieDetailsUiModel, fontSizeViewModel: FontSizeViewModel , navController: NavController) {
    val scale = fontSizeViewModel.fontScale.value

    Scaffold(topBar = {
        TopAppBar(
            showBackButton = true,
            title = R.string.moodToMovie
        )
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF0C3260), Color(0xFF061E3B))
                    )
                )
        ) {
            Column {

                // Poster image with gradient overlay
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    AsyncImage(
                        model = movie.image,
                        contentDescription = movie.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color(0xC0061E3B)),
                                    startY = 300f * scale,
                                    endY = 1200f * scale
                                )
                            )
                    )
                }

                // Movie details
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp * scale, vertical = 12.dp * scale),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {

                        Text(
                            text = movie.title,
                            color = Color.White,
                            fontSize = 30.sp * scale,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp * scale))

                        Text(
                            text = "${movie.year} | ${movie.rating}+min",
                            color = Color(0xFFCCCCCC),
                            fontSize = 20.sp * scale,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp * scale))

                        Text(
                            text = movie.overview,
                            color = Color(0xFFCCCCCC),
                            fontSize = 18.sp * scale,
                            fontWeight = FontWeight.Light,
                            lineHeight = 21.sp * scale,
                            maxLines = 5
                        )
                        Spacer(modifier = Modifier.height(20.dp * scale))

                        Text(
                            text = "Cast",
                            color = Color.White,
                            fontSize = 16.sp * scale,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp * scale))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy((-20).dp * scale)
                            ) {
                                movie.castImages.forEach {
                                    Image(
                                        painter = painterResource(id = it),
                                        contentDescription = "Cast Member",
                                        modifier = Modifier
                                            .size(48.dp * scale)
                                            .clip(CircleShape)
                                    )
                                }
                            }

                            Text(
                                text = "Trailer",
                                color = Color(0xFF0096FF),
                                fontSize = 18.sp * scale,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // Bottom action row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { /* TODO: Save movie */ }) {
                            Icon(
                                imageVector = Icons.Default.BookmarkBorder,
                                contentDescription = "Save",
                                tint = Color.White,
                                modifier = Modifier.size(40.dp * scale)
                            )
                        }
                        Spacer(modifier = Modifier.width(20.dp * scale))

                        Button(
                            onClick = {  navController.navigate("movie_content/${movie.id}") },
                            modifier = Modifier
                                .width(230.dp * scale)
                                .height(65.dp * scale)
                                .shadow(
                                    elevation = 20.dp * scale,
                                    shape = RoundedCornerShape(20.dp * scale),
                                    ambientColor = Color(0xFFEC255A),
                                    spotColor = Color(0xFFEC255A)
                                ),
                            shape = RoundedCornerShape(16.dp * scale),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEC255A))
                        ) {
                            Text(
                                text = "Watch Now",
                                color = Color.White,
                                fontSize = 18.sp * scale,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

