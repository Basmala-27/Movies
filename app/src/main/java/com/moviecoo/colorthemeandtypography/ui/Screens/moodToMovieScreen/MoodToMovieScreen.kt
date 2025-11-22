package com.moviecoo.colorthemeandtypography.ui.screens.moodToMovieScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.moviecoo.colorthemeandtypography.ui.screens.moodToMovie.MovieMoodViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.common_components.TopAppBar
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.MovieListItem
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.moodToMovie.MoodToMovieRepository

@Composable
fun MoodToMovieScreen(viewModel: MoodToMovieRepository, genreId: String) {
    val movies = remember { mutableStateOf<List<MovieUiModel>>(emptyList()) }
    val randomMovie = remember { mutableStateOf<MovieUiModel?>(null) }

    LaunchedEffect(genreId) {
        val response = viewModel.getMoviesByMood(genreId)
        movies.value = response
        randomMovie.value = movies.value.randomOrNull()
    }

    randomMovie.value?.let { movie ->
        Scaffold (topBar = {
            TopAppBar(
                true,
                title = R.string.moodToMovie,
            )
        } ) { innerPadding ->



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
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
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
                                    textAlign = TextAlign.Center,

                                    )
                                Spacer(modifier = Modifier.height(20.dp))

                                Text(
                                    text = "${movie.year} | ${movie.rating}+min ",
                                    color = Color(0xFFCCCCCC),
                                    fontSize = 24.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.height(16.dp))

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = movie.rating.toString(),
                                        color = Color.White,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "★★★★☆",
                                        color = Color(0xFFFFD700),
                                        fontSize = 20.sp
                                    )
                                }
                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = movie.description,
                                    color = Color(0xFFCCCCCC),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Light,
                                    lineHeight = 21.sp,
                                    maxLines = 5,
                                )

                                Spacer(modifier = Modifier.height(20.dp))
                                Spacer(modifier = Modifier.width(20.dp))
                                Spacer(modifier = Modifier.weight(1f))

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
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(
                                                0xFFEC255A
                                            )
                                        )
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
        }
        ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Loading...")
        }
    }
}
