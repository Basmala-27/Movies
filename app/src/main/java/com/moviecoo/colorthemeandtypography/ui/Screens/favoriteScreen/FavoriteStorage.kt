package com.moviecoo.colorthemeandtypography.ui.Screens.favoriteScreen

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel

object FavoriteStorage {

    private const val PREF_NAME = "favorite_prefs"
    private const val KEY_FAVORITES = "favorites"
    private val gson = Gson()

    private fun prefs(context: Context) =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    // حفظ فيلم
    fun saveMovie(context: Context, movie: MovieUiModel) {
        val list = getMovies(context).toMutableList()
        if (list.none { it.id == movie.id }) {
            list.add(movie)
            val json = gson.toJson(list)
            prefs(context).edit().putString(KEY_FAVORITES, json).apply()
        }
    }

    // إزالة فيلم
    fun removeMovie(context: Context, movieId: Int) {
        val list = getMovies(context).filterNot { it.id == movieId }
        val json = gson.toJson(list)
        prefs(context).edit().putString(KEY_FAVORITES, json).apply()
    }

    // التحقق إذا الفيلم موجود بالفعل
    fun isSaved(context: Context, movieId: Int): Boolean {
        return getMovies(context).any { it.id == movieId }
    }

    // جلب كل الأفلام المفضلة
    fun getMovies(context: Context): List<MovieUiModel> {
        val json = prefs(context).getString(KEY_FAVORITES, null) ?: return emptyList()
        val type = object : TypeToken<List<MovieUiModel>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }
}