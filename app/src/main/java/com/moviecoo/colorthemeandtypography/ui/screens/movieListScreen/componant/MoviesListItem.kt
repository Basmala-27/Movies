package com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.componant

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.sampleNewReleases
import com.moviecoo.colorthemeandtypography.ui.theme.OnPrimary

@Composable
fun MovieListItem(movie: MovieUiModel, showRating: Boolean , onMovieClick: (String) -> Unit = { _ -> }) {

        Column(modifier = Modifier.width(160.dp)) {

            Card(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .clickable{onMovieClick},
                colors = CardDefaults.cardColors(containerColor = OnPrimary)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(movie.image).crossfade(1000)
                                .build()
                        ),

                        contentDescription = movie.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )


                    if (showRating) {
                        RatingBadge(rating = movie.rating)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))


            Text(movie.title, color = Color.White, fontWeight = FontWeight.SemiBold, maxLines = 1)

            Row {
                Text(movie.year, color = Color.Gray, fontSize = 12.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text("•", color = Color.Gray, fontSize = 12.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text("${movie.rating}", color = Color.Gray, fontSize = 12.sp)

                if (showRating) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("•", color = Color.Gray, fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(movie.description, color = Color.Gray, fontSize = 12.sp, maxLines = 1)
                }
            }
        }
    }


@Composable
@Preview(showBackground = true)
fun MovieListItemPreview() {
    MovieListItem(
        movie = MovieUiModel("The Matrix", "2023", "Action", "Sci-Fi", 8.7, "https://picsum.photos/200/300") ,
        showRating = true,
        onMovieClick = {}
    )

}