package com.moviecoo.colorthemeandtypography.ui.Screens.WatchListScreen


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.common_components.TopAppBar
import com.moviecoo.colorthemeandtypography.ui.Screens.WatchListScreen.component.MovieWatchListItem
import com.moviecoo.colorthemeandtypography.ui.Screens.WatchListScreen.model.MovieDataUiModel
import com.moviecoo.colorthemeandtypography.common_components.MovieBottomBar
import com.moviecoo.colorthemeandtypography.ui.theme.Primary
import com.moviecoo.colorthemeandtypography.ui.theme.Surface


@Composable
fun WatchListScreen() {
    val movies = listOf(
        MovieDataUiModel(
            name = "Spiderman",
            rate = 8.5,
            tickets = 10,
            date = 2019,
            durationMinutes = 139,
            poster = R.drawable.homem_aranha
        ),


        MovieDataUiModel(
            name = "Spider-Man: No Way Home",
            rate = 8.7,
            tickets = 10,
            date = 2021,
            durationMinutes = 139,
            poster = R.drawable.homem_aranha2

        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                true,
                backgroundColor = Surface,
                title = R.string.app_name,
            )
        },
        bottomBar = {
            MovieBottomBar()
        },
        containerColor = Primary
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(movies) { movie ->
                MovieWatchListItem(movieDataUiModel = movie)
            }
        }
    }




}




@Preview(showBackground = true,
    showSystemUi = true)
@Composable
private fun PreviewWatchListScreen() {
    WatchListScreen()
}