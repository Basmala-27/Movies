package com.moviecoo.colorthemeandtypography.ui.Screens.detailsScreen

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moviecoo.colorthemeandtypography.R


@Composable
fun DetailsScreen(movie: MovieDetailsUiModel) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF0C3260), Color(0xFF061E3B))
                )
            )
    ) {
        Column {
            // صورة الخلفية
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.venom_background),
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

            // التفاصيل
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
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "${movie.year} | ${movie.genre} | ${movie.duration}",
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
                        text = movie.overview,
                        color = Color(0xFFCCCCCC),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light,
                        lineHeight = 21.sp
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Cast",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(-20.dp)
                        ) {
                            movie.castImages.forEach {
                                Image(
                                    painter = painterResource(id = it),
                                    contentDescription = "Cast Member",
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(CircleShape)
                                )
                            }
                        }

                        Text(
                            text = "Trailer",
                            color = Color(0xFF0096FF),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

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

@Preview(showBackground = true)
@Composable
fun PreviewDetailsScreen() {
    val sampleMovie = MovieDetailsUiModel(
        title = "Venom 2",
        year = "2021",
        genre = "Action, Fantasy",
        duration = "1h 37min",
        rating = 6.1,
        overview = "Eddie Brock attempts to reignite his career by interviewing serial killer Cletus Kasady, who becomes the host of the symbiote Carnage.",
        image = "venom_background",
        castImages = listOf(
            R.drawable.cast1,
            R.drawable.cast2,
            R.drawable.cast3,
            R.drawable.cast4
        )
    )

    DetailsScreen(movie = sampleMovie)
}
