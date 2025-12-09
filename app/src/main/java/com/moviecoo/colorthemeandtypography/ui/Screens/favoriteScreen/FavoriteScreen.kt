package com.moviecoo.colorthemeandtypography.ui.Screens.favoriteScreen

import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.common_components.TopAppBar
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.FontSizeViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.WatchListScreen.component.MovieWatchListItem
import com.moviecoo.colorthemeandtypography.ui.theme.Primary
import com.moviecoo.colorthemeandtypography.ui.theme.Surface

@Composable
fun FavoriteScreen(
    navController: NavController,
    fontSizeViewModel: FontSizeViewModel // من غير = hiltViewModel()
) {
    val context = LocalContext.current
    var favorites by remember { mutableStateOf(FavoriteStorage.getMovies(context)) }
    Scaffold(
        topBar = {
            TopAppBar(
                true,
                backgroundColor = Surface,
                title = R.string.favoritelist,
            )
        }
        ,
        containerColor = Primary
    ) { innerPadding ->

        LazyColumn(modifier = Modifier.padding(innerPadding)) {

            items(favorites) { movie ->
                MovieWatchListItem(
                    movieUiModel = movie,
                    fontSizeViewModel = fontSizeViewModel,
                    onClick = {
                        navController.navigate("movie_details/${movie.id}")
                    },
                    onFavoriteClick = {
                        if (FavoriteStorage.isSaved(context, movie.id)) {
                            FavoriteStorage.removeMovie(context, movie.id)
                        } else {
                            FavoriteStorage.saveMovie(context, movie)
                        }
                        // ← تحديث القائمة بعد أي تغيير
                        favorites = FavoriteStorage.getMovies(context)
                    }
                )
            }

        }
    }
}
