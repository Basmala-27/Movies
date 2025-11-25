package com.moviecoo.colorthemeandtypography.ui.screens.searchScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.MovieListItem
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel
import com.moviecoo.colorthemeandtypography.ui.theme.Primary


@Composable
fun SearchScreen(
    navController: NavController,
    moviesList: List<MovieUiModel>
) {
    var query by remember { mutableStateOf("") }
    val searchedMovies = remember { mutableStateOf(moviesList) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
            .padding(16.dp)
    ) {
        // Search TextField
        TextField(
            value = query,
            onValueChange = { query = it },
            placeholder = { Text("Search movies...", color = Color.LightGray) },
            singleLine = true,

            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    searchedMovies.value = moviesList.filter {
                        it.title.contains(query, ignoreCase = true)
                    }
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Display Results
        if (searchedMovies.value.isEmpty()) {
            Text(
                "No movies found",
                color = Color.LightGray,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(searchedMovies.value) { movie ->
                    MovieListItem(
                        movie = movie,
                        showRating = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                // Navigate to SeeAllScreen with movie title
                                navController.navigate("See_All_Screen/${movie.title}")
                            }
                    )
                }
            }
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true

)

@Composable
private fun PreviewSearchScreen() {
    val dummyMovies = listOf(
        MovieUiModel(
            title = "Inception", rating = 8.8,
            year = "2015",
            description = "",
            genre = "",
            image = "TODO()",
            id = 1
        ),

    )

    val fakeNavController = rememberNavController()


    SearchScreen(
        navController = fakeNavController,
        moviesList = dummyMovies
    )

}
