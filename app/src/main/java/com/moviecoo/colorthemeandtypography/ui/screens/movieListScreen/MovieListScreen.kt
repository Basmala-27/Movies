package com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen


import androidx.compose.foundation.Image
import com.moviecoo.colorthemeandtypography.R

import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.foundation.background
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.android.material.bottomappbar.BottomAppBar
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.viewmodel.MovieListViewModel
import com.moviecoo.colorthemeandtypography.common_components.MovieBottomBar
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.NetworkModule.provideMovieApi
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.MovieDataModel
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.Result
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.componant.AppScreenHeader
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.data.Movies
import com.moviecoo.colorthemeandtypography.ui.theme.ColorThemeandTypographyTheme
import com.moviecoo.colorthemeandtypography.ui.theme.OnPrimary
import com.moviecoo.colorthemeandtypography.ui.theme.OrangeAccent
import com.moviecoo.colorthemeandtypography.ui.theme.Primary
import com.moviecoo.colorthemeandtypography.ui.theme.Secondary
import com.moviecoo.colorthemeandtypography.mapper.toMoviesUiModel












@Composable
fun MovieListScreen(onSeeAllClick: (String) -> Unit = { _ -> }, navController: NavController) {
//    val viewmodel: MovieListViewModel = viewModel()


    val viewModel: MovieListViewModel = hiltViewModel() // لو Compose






//    LaunchedEffect(Unit) {
//        viewmodel.fetchMovies()
//    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Primary)
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
            )
         {
             AppScreenHeader(navController = navController)
            Spacer(modifier = Modifier.height(16.dp))

            FeaturedMovieCard(
                title = "Quantum Paradox",
                details = "Sci-Fi • 2021 • 136m"
            )
            Spacer(modifier = Modifier.height(24.dp))
             MovieSection(title = "Upcoming", onSeeAllClick = onSeeAllClick, showRating = false)
             Spacer(modifier = Modifier.height(24.dp))
            MovieSection(title = "Trending Now",onSeeAllClick, showRating = true)
            Spacer(modifier = Modifier.height(24.dp))

             MovieSection(title = "Top Rated", onSeeAllClick,showRating = true)
             Spacer(modifier = Modifier.height(24.dp))

            MovieSection(title = "New Releases",  onSeeAllClick ,showRating = false)
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}




@Composable
fun FeaturedMovieCard(title: String, details: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .matchParentSize()

            ){
                Image(painter = painterResource(R.drawable.movie), contentDescription = "Movie Image" , contentScale = ContentScale.FillBounds, modifier = Modifier.fillMaxSize())
            }


            Box(

                modifier = Modifier.fillMaxSize()
                    
            )


            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(containerColor = Secondary)
                    ) {
                        Text(text = "Watch Now", color = Color.White)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)

                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add to Watchlist", tint = Color.Black, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Watchlist", color = Color.Black)
                    }
                }
            }
        }
    }

    Column ( modifier = Modifier.fillMaxSize().padding(start = 28.dp), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center  ){
        Text(title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = Color.White)
        Text(details, style = MaterialTheme.typography.bodyMedium, color = Color.LightGray , modifier = Modifier.padding(start = 10.dp))
    }
}

@Composable

fun MovieSection(title: String,onSeeAllClick:(String) -> Unit = {_ -> },showRating: Boolean) {

    val movieListState = remember {
       mutableStateOf<MovieDataModel?>(null)
      }
LaunchedEffect(Unit) {
    if (title == "Trending Now"){
    val response = provideMovieApi().fetchMovies()
    movieListState.value = response.body() as MovieDataModel
}
    else if (title == "New Releases"){
        val response = provideMovieApi().fetchNowPlayingMovies()
        movieListState.value = response.body() as MovieDataModel
    }
    else if (title == "Upcoming"){
        val response = provideMovieApi().fetchUpcomingMovies()
        movieListState.value = response.body() as MovieDataModel

    }
    else if (title == "Top Rated"){
        val response = provideMovieApi().fetchTopRatingMovies()
        movieListState.value = response.body() as MovieDataModel
    }
}
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.White)
        TextButton(onClick = { onSeeAllClick(title) }) {
            Text("See All >", color = OrangeAccent, fontSize = 14.sp)
        }
    }


    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        movieListState.value?.let {
            items( it.toMoviesUiModel()) {  movieUiModelItem->
            MovieListItem(movie = movieUiModelItem, showRating = showRating)
        }
        }

    }
}



@Composable
fun MovieListItem(movie: MovieUiModel, showRating: Boolean, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.width(160.dp)) {

        Card(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            colors = CardDefaults.cardColors(containerColor =  OnPrimary)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
               Image ( painter=rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(movie.image).crossfade(1000)
                        .build()),

                    contentDescription = movie.title,
                    modifier = Modifier.fillMaxSize() ,
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
                Text(movie.description, color = Color.Gray, fontSize = 12.sp , maxLines = 1)
            }
        }
    }
}

@Composable
fun RatingBadge(rating: Double) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Color.Black.copy(alpha = 0.6f))
            .padding(horizontal = 6.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Default.Star,
            contentDescription = "Rating Star",
            tint = OrangeAccent,
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = rating.toString(),
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}




@Preview(showBackground = true)
@Composable
fun PreviewMovieAppScreen() {
    ColorThemeandTypographyTheme {
        MovieListScreen(
            onSeeAllClick = TODO(),
            navController = TODO()
        )
    }
}