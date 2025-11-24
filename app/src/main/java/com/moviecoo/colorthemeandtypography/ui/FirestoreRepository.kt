//package com.moviecoo.colorthemeandtypography.ui
//
//import com.google.firebase.firestore.FirebaseFirestore
//import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel
//
//class FirestoreRepository {
//
//    private val db = FirebaseFirestore.getInstance()
//
//    fun addMovieToWatchlist(userId: String, movie:MovieUiModel, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
//        db.collection("users")
//            .document(userId)
//            .collection("watchlist")
//            .add(movie)
//            .addOnSuccessListener { onSuccess() }
//            .addOnFailureListener { exception -> onFailure(exception) }
//    }
//}