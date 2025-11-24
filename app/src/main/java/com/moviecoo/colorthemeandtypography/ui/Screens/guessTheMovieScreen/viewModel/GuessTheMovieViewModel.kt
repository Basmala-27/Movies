package com.moviecoo.colorthemeandtypography.ui.screens.guessTheMovieScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.moviecoo.colorthemeandtypography.ui.screens.guessTheMovieScreen.data.guessTheMovieData

class GuessMovieViewModel : ViewModel() {
    var movies by mutableStateOf(guessTheMovieData)
    var currentIndex by mutableStateOf(0)
    var score by mutableStateOf(0)
    var selectedAnswer by mutableStateOf<String?>(null)

    fun selectAnswer(answer: String) {
        selectedAnswer = answer
        if (answer == movies[currentIndex].correctAnswer) score++
    }

    fun nextMovie() {
        selectedAnswer = null
        if (currentIndex < movies.size - 1) currentIndex++
        else currentIndex = 0 // رجوع لأول فيلم بعد النهاية
    }
}
