package com.moviecoo.colorthemeandtypography.ui.Screens.WatchListScreen.component

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.data.MovieDetailsUiModel

object WatchlistStorage {

    private const val PREF_NAME = "watchlist_prefs"
    private const val KEY_WATCHLIST = "watchlist"
    private val gson = Gson()

    private fun prefs(context: Context) =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveMovie(context: Context, movie: MovieDetailsUiModel) {
        val list = getMovies(context).toMutableList()
        if (list.none { it.id == movie.id }) {
            list.add(movie)
            val json = gson.toJson(list)
            prefs(context).edit().putString(KEY_WATCHLIST, json).apply()
        }
    }

    fun removeMovie(context: Context, movieId: Int) {
        val list = getMovies(context).filterNot { it.id == movieId }
        val json = gson.toJson(list)
        prefs(context).edit().putString(KEY_WATCHLIST, json).apply()
    }

    fun isSaved(context: Context, movieId: Int): Boolean {
        return getMovies(context).any { it.id == movieId }
    }

    fun getMovies(context: Context): List<MovieDetailsUiModel> {
        val json = prefs(context).getString(KEY_WATCHLIST, null) ?: return emptyList()
        val type = object : TypeToken<List<MovieDetailsUiModel>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }
}
