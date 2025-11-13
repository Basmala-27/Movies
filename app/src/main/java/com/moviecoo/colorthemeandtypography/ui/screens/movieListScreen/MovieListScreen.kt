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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.moviecoo.colorthemeandtypography.data.data_source.remote.RemoteDataSource
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.provideMovieApi
import com.moviecoo.colorthemeandtypography.data.data_source.repository.MoviesRepositoryImp
import com.moviecoo.colorthemeandtypography.domain.usecase.FetchMoviesUseCase
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.viewmodel.MovieListViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.componant.AppScreenHeader
import com.moviecoo.colorthemeandtypography.ui.theme.ColorThemeandTypographyTheme
import com.moviecoo.colorthemeandtypography.ui.theme.OrangeAccent
import com.moviecoo.colorthemeandtypography.ui.theme.Primary
import com.moviecoo.colorthemeandtypography.ui.theme.Secondary
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.componant.MovieListItem
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.viewmodel.MovieListViewModelFactory


@Composable
fun MovieListScreen(onSeeAllClick: (String) -> Unit = { _ -> } , onMovieClick: (String) -> Unit = { _ -> }) {


    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Primary)
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
            )
         {
             AppScreenHeader()
            Spacer(modifier = Modifier.height(16.dp))

            FeaturedMovieCard(
                title = "Quantum Paradox",
                details = "Sci-Fi • 2021 • 136m"
            )
            Spacer(modifier = Modifier.height(24.dp))
             MovieSection(title = "Upcoming", onSeeAllClick = onSeeAllClick, showRating = false , onMovieClick = onMovieClick)
             Spacer(modifier = Modifier.height(24.dp))
            MovieSection(title = "Trending Now",onSeeAllClick, showRating = true , onMovieClick)
            Spacer(modifier = Modifier.height(24.dp))

             MovieSection(title = "Top Rated", onSeeAllClick,showRating = true ,onMovieClick)
             Spacer(modifier = Modifier.height(24.dp))

             MovieSection(title = "New Releases",  onSeeAllClick ,showRating = false , onMovieClick)
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

fun MovieSection(
    title: String,
    onSeeAllClick: (String) -> Unit = { _ -> },
    showRating: Boolean,
    onMovieClick: (String) -> Unit = { _ -> }
) {
   val fetchMoviesUseCase = FetchMoviesUseCase(
       MoviesRepositoryImp(RemoteDataSource(provideMovieApi()))
   )
    val factory = MovieListViewModelFactory(fetchMoviesUseCase)

    val viewmodel = ViewModelProvider(LocalViewModelStoreOwner.current!!, factory).get(MovieListViewModel::class.java)
    LaunchedEffect(Unit) {
        when (title) {
            "Trending Now" -> viewmodel.requestMovies()
            "New Releases" -> viewmodel.requestMovies()
            "Upcoming" -> viewmodel.requestMovies()
            "Top Rated" -> viewmodel.requestMovies()
        }
    }

   val movieListUiState by viewmodel.movieListStateFlow.collectAsStateWithLifecycle()
        MovieListContent(
            title = title,
            onSeeAllClick = onSeeAllClick,
            showRating = showRating,
            movieListUiState = movieListUiState,
            onMovieClick = onMovieClick
        )
    }



@Composable
fun MovieListContent(
    title: String,
    onSeeAllClick: (String) -> Unit = { _ -> },
    showRating: Boolean, movieListUiState: MovieListUiState, onMovieClick: (String) -> Unit = { _ -> }){

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

    when(movieListUiState){
        MovieListUiState.Initial -> {}
        is MovieListUiState.Loading ->
            if (movieListUiState.isLoading)
            {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        is MovieListUiState.MoviesList -> {
            MovieListLazyColume(movieList = movieListUiState.movies, showRating = showRating, onMovieClick = onMovieClick)
        }
        is MovieListUiState.Error -> {
            Text(text = movieListUiState.message)
        }
    }



}
@Composable
fun MovieListLazyColume(
    movieList: List<MovieUiModel>,
    showRating: Boolean,
    onMovieClick: (String) -> Unit = { _ -> }
){
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items( movieList) {  movieUiModelItem->
            MovieListItem(movie = movieUiModelItem, showRating = showRating ){ title ->
                onMovieClick(title)
            }
        }


    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun PreviewMovieListScreenFull() {
    ColorThemeandTypographyTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Primary)
                .verticalScroll(rememberScrollState())
        ) {
            AppScreenHeader()
            Spacer(modifier = Modifier.height(16.dp))

            FeaturedMovieCard(
                title = "Quantum Paradox",
                details = "Sci-Fi • 2021 • 136m"
            )
            Spacer(modifier = Modifier.height(24.dp))


            MovieListContent(
                title = "Upcoming",
                onSeeAllClick = {},
                showRating = false,
                movieListUiState = MovieListUiState.MoviesList(sampleNewReleases),
                onMovieClick = {}
            )
            Spacer(modifier = Modifier.height(24.dp))

            MovieListContent(
                title = "Trending Now",
                onSeeAllClick = {},
                showRating = true,
                movieListUiState = MovieListUiState.MoviesList(sampleNewReleases),
                onMovieClick = {}
            )
            Spacer(modifier = Modifier.height(24.dp))

            MovieListContent(
                title = "Top Rated",
                onSeeAllClick = {},
                showRating = true,
                movieListUiState =  MovieListUiState.MoviesList(sampleNewReleases),
                onMovieClick = {}
            )
            Spacer(modifier = Modifier.height(24.dp))

            MovieListContent(
                title = "New Releases",
                onSeeAllClick = {},
                showRating = false,
                movieListUiState =  MovieListUiState.MoviesList(sampleNewReleases),
                onMovieClick = {}
            )
        }
    }
}