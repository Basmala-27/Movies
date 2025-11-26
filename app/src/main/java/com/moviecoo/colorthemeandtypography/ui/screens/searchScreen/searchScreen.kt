package com.moviecoo.colorthemeandtypography.ui.screens.searchScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.NetworkModule.provideMovieApi
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.MovieDataModel
import com.moviecoo.colorthemeandtypography.mapper.toMoviesUiModel
import com.moviecoo.colorthemeandtypography.ui.Screens.signInScreen.fontSizeViewModel.FontSizeViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.MovieListItem
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.viewmodel.MovieListViewModel
import com.moviecoo.colorthemeandtypography.ui.theme.Primary


@Composable
fun SearchScreen(
    navController: NavController,
    fontSizeViewModel: FontSizeViewModel
) {
    val movieListState = remember { mutableStateOf<MovieDataModel?>(null) }



    LaunchedEffect(Unit) {
        val response = provideMovieApi().fetchMovies()
        movieListState.value = response?.body() as? MovieDataModel

    }
    val List = movieListState.value?.toMoviesUiModel()?.let { moviesList ->


        val scale = fontSizeViewModel.fontScale.value
        var query by remember { mutableStateOf("") }
        var searchedMovies by remember { mutableStateOf(moviesList) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Primary)
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Text("Search", color = Color.White, fontSize = 26.sp)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                shape = RoundedCornerShape(25.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(12.dp)
                ) {
                    Icon(Icons.Default.Search, "Search", tint = Primary)

                    TextField(
                        value = query,
                        onValueChange = {
                            query = it
                            searchedMovies = if (query.isBlank()) {
                                moviesList
                            } else {
                                moviesList.filter { movie ->
                                    movie.title.contains(query, ignoreCase = true)
                                }
                            }
                        },
                        placeholder = { Text("Search movies…", color = Color.Gray) },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Primary
                        ),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                searchedMovies = moviesList.filter { movie ->
                                    movie.title.contains(query, ignoreCase = true)
                                }
                            }
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            println("SEARCHED SIZE = ${searchedMovies.size}")
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                items(searchedMovies) { movie ->

                    Log.d("SEARCH", "IMAGE IN SEARCH: ${movie.image}")


                    MovieListItem(
                        movie = movie,
                        showRating = true,
                        onClick = { navController.navigate("movie_details/${movie.id}") },
                        modifier = Modifier
                            .fillMaxWidth(),
                        scale = scale
                    )
                }
            }
        }
    }

}

//@Preview(
//    showBackground = true,
//    showSystemUi = true
//
//)

//@Composable
//private fun PreviewSearchScreen() {
//    val dummyMovies = listOf(
//        MovieUiModel(
//            title = "Inception", rating = 8.8,
//            year = "2015",
//            description = "",
//            genre = "",
//            image = "TODO()",
//            id = 1
//        ),
//
//        )
//
//    val fakeNavController = rememberNavController()
//
//
//    SearchScreen(
//        navController = fakeNavController,
//        moviesList = dummyMovies ,
//        fontSizeViewModel = FontSizeViewModel() ,
//        modifier = Modifier.fillMaxSize()
//    )
//
//}