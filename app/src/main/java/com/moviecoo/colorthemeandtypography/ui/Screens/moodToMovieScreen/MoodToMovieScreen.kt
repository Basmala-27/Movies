// MoodToMovieScreen
package com.moviecoo.colorthemeandtypography.ui.screens.moodToMovieScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.moviecoo.colorthemeandtypography.common_components.TopAppBar
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.moodToMovie.MoodToMovieRepository
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.shape.RoundedCornerShape
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.ui.screens.randomMovieScreen.RandomMovieSpinScreen
import kotlinx.coroutines.delay

@Composable
fun MoodToMovieScreen(viewModel: MoodToMovieRepository, genreId: String? = null) {
    val movies = remember { mutableStateOf<List<MovieUiModel>>(emptyList()) }
    val selectedMovie = remember { mutableStateOf<MovieUiModel?>(null) }

    LaunchedEffect(genreId) {
        delay(1500)
        selectedMovie.value = if (genreId != null) {
            val list = viewModel.getMoviesByMood(genreId)
            movies.value = list
            list.randomOrNull()
        } else {
            viewModel.getRandomMovie()
        }


    }

    selectedMovie.value?.let { movie ->
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
                                        startY = 300f,
                                        endY = 1200f
                                    )
                                )
                        )
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 12.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = movie.title,
                                color = Color.White,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "${movie.year} | ${movie.rating}+min",
                                color = Color(0xFFCCCCCC),
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = movie.description,
                                color = Color(0xFFCCCCCC),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light,
                                lineHeight = 21.sp,
                                maxLines = 5
                            )
                            Spacer(modifier = Modifier.height(20.dp))

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
                                        modifier = Modifier.size(40.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(20.dp))
                                Button(
                                    onClick = { /* TODO: Watch now */ },
                                    modifier = Modifier
                                        .width(230.dp)
                                        .height(65.dp)
                                        .shadow(
                                            elevation = 20.dp,
                                            shape = RoundedCornerShape(20.dp),
                                            ambientColor = Color(0xFFEC255A),
                                            spotColor = Color(0xFFEC255A)
                                        ),
                                    shape = RoundedCornerShape(16.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEC255A))
                                ) {
                                    Text(
                                        text = "Watch Now",
                                        color = Color.White,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    } ?: run {

        RandomMovieSpinScreen()
    }
}
