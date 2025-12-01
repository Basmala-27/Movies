//package com.moviecoo.colorthemeandtypography.ui.Screens.favoriteScreen
//
//import android.content.Context
//import androidx.lifecycle.ViewModel
//import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel
//import dagger.hilt.android.lifecycle.HiltViewModel
//import dagger.hilt.android.qualifiers.ApplicationContext
//import jakarta.inject.Inject
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//
//class FavoritesViewModel ( private val context: Context) : ViewModel() {
//    private val _favorites = MutableStateFlow<List<MovieUiModel>>(FavoriteStorage.getMovies(context))
//    val favorites: StateFlow<List<MovieUiModel>> = _favorites
//
//    fun toggleFavorite(movie: MovieUiModel) {
//        if (FavoriteStorage.isSaved(context, movie.id)) {
//            FavoriteStorage.removeMovie(context, movie.id)
//        } else {
//            FavoriteStorage.saveMovie(context, movie)
//        }
//        _favorites.value = FavoriteStorage.getMovies(context)
//    }
//}
