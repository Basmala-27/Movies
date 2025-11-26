package com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.videoViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.videoRepository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VideoViewModel(private val repository: VideoRepository) : ViewModel() {

    private val _videoUrl = MutableStateFlow<String?>(null)
    val videoUrl: StateFlow<String?> = _videoUrl

    private val _showVideo = MutableStateFlow(false)
    val showVideo: StateFlow<Boolean> = _showVideo

    fun watchNow(movieId: Int) {
        viewModelScope.launch {
            val key = repository.getMovieTrailer(movieId)
            if (key != null) {
                _videoUrl.value = "https://www.youtube.com/watch?v=$key"
                _showVideo.value = true
            }
        }
    }

    fun closeVideo() {
        _showVideo.value = false
    }
}
