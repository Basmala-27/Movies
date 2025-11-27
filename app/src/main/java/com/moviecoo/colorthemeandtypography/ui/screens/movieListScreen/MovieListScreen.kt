package com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen


import androidx.compose.foundation.Image
import com.moviecoo.colorthemeandtypography.R

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.viewmodel.MovieListViewModel
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.NetworkModule.provideMovieApi
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.MovieDataModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.componant.AppScreenHeader
import com.moviecoo.colorthemeandtypography.ui.theme.OnPrimary
import com.moviecoo.colorthemeandtypography.ui.theme.OrangeAccent
import com.moviecoo.colorthemeandtypography.ui.theme.Primary
import com.moviecoo.colorthemeandtypography.mapper.toMoviesUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.FontSizeViewModel

@Composable
fun MovieListScreen(
    navController: NavController,
    fontSizeViewModel: FontSizeViewModel,
    onFeaturedClick: () -> Unit = {},
    onRandomClick: () -> Unit = {},
    onGuessClick: () -> Unit = {},
    onSeeAllClick: (String) -> Unit = { _ -> },
    onMovieClick: (MovieUiModel) -> Unit = {}
) {
    val scale = fontSizeViewModel.fontScale.value

    val viewModel: MovieListViewModel = hiltViewModel()

    // Features
    val featuresList = listOf(
        features("Movie to Mood", R.drawable.moodtomovie, onFeaturedClick),
        features("Random Movie", R.drawable.randommovie, onRandomClick),
        features("Guess The Movie", R.drawable.guess, onGuessClick)
    )

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Primary)
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(25.dp * scale))
            AppScreenHeader(navController = navController)
            Spacer(modifier = Modifier.height(16.dp * scale))

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 2.dp * scale),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(featuresList) { item ->
                    FeaturedMovieitem(onClick = item.onClick, image = item.image, scale = scale)
                }
            }


            Spacer(modifier = Modifier.height(24.dp * scale))

            // Movie Sections
            listOf(
                "Upcoming" to false,
                "Trending Now" to true,
                "Top Rated" to true,
                "New Releases" to false
            ).forEach { (title, showRating) ->
                Spacer(modifier = Modifier.height(24.dp * scale))
                MovieSection(
                    title = title,
                    showRating = showRating,
                    onMovieClick = onMovieClick,
                    onSeeAllClick = onSeeAllClick,
                    scale = scale
                )
            }
        }
    }
}

data class features(
    val title: String ,
    val image: Int ,
    val onClick: () -> Unit ={}
)





@Composable
fun FeaturedMovieitem(onClick: () -> Unit , image: Int, scale: Float){
    Card(
        modifier = Modifier
            .width(400.dp * scale)
            .height(220.dp * scale)
            .padding(horizontal = 16.dp * scale)
            .clip(RoundedCornerShape(12.dp * scale))
            .clickable { onClick() }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(image),
                contentDescription = "Movie Image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}


@Composable
fun MovieSection(
    title: String,
    showRating: Boolean,
    onSeeAllClick: (String) -> Unit = {},
    onMovieClick: (MovieUiModel) -> Unit = {},
    scale: Float
) {
    val movieListState = remember { mutableStateOf<MovieDataModel?>(null) }

    LaunchedEffect(Unit) {
        val response = when(title) {
            "Trending Now" -> provideMovieApi().fetchMovies()
            "New Releases" -> provideMovieApi().fetchNowPlayingMovies()
            "Upcoming" -> provideMovieApi().fetchUpcomingMovies()
            "Top Rated" -> provideMovieApi().fetchTopRatingMovies()
            else -> null
        }
        movieListState.value = response?.body() as? MovieDataModel
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp * scale, vertical = 8.dp * scale),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, fontSize = 20.sp * scale, color = Color.White)
        TextButton(onClick = { onSeeAllClick(title) }) {
            Text("See All >", color = OrangeAccent, fontSize = 14.sp * scale)
        }
    }

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp * scale),
        horizontalArrangement = Arrangement.spacedBy(12.dp * scale)
    ) {
        movieListState.value?.toMoviesUiModel()?.let { list ->
            items(list) { movie ->
                MovieListItem(movie = movie, showRating = showRating, onClick = { onMovieClick(movie) }, scale = scale)
            }
        }
    }
}



@Composable
fun MovieListItem(
    movie: MovieUiModel,
    showRating: Boolean,
    onClick: () -> Unit ={} ,
    modifier: Modifier = Modifier,
    scale: Float
) {
    Column(modifier = modifier.width(160.dp * scale)) {
        Card(
            modifier = Modifier
                .height(200.dp * scale)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp * scale))
                .clickable { onClick() },
            colors = CardDefaults.cardColors(containerColor = OnPrimary)
        ) {
            AsyncImage(
                model = movie.image,
                contentDescription = movie.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            if (showRating) RatingBadge(movie.rating, scale)
        }
        Spacer(modifier = Modifier.height(8.dp * scale))
        Text(movie.title, color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 14.sp * scale, maxLines = 1)
    }
}

@Composable
fun RatingBadge(rating: Double, scale: Float) {
    Row(
        modifier = Modifier
            .padding(8.dp * scale)
            .clip(RoundedCornerShape(4.dp * scale))
            .background(Color.Black.copy(alpha = 0.6f))
            .padding(horizontal = 6.dp * scale, vertical = 4.dp * scale),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Default.Star,
            contentDescription = "Rating Star",
            tint = OrangeAccent,
            modifier = Modifier.size(14.dp * scale)
        )
        Spacer(modifier = Modifier.width(4.dp * scale))
        Text(
            text = rating.toString(),
            color = Color.White,
            fontSize = 12.sp * scale,
            fontWeight = FontWeight.Bold
        )
    }
}


