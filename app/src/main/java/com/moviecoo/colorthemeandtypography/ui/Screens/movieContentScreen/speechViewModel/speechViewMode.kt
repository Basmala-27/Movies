package com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.speechViewModel



import android.app.Application
import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale


class SpeechViewModel(application: Application) : AndroidViewModel(application), TextToSpeech.OnInitListener {

    private var tts: TextToSpeech = TextToSpeech(application, this)

    private val _isSpeaking = MutableStateFlow(false)
    val isSpeaking = _isSpeaking.asStateFlow()

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.US
        }
    }

    fun speak(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "TTS_ID")
        _isSpeaking.value = true
    }

    fun stopSpeaking() {
        tts.stop()
        _isSpeaking.value = false
    }

    override fun onCleared() {
        tts.stop()
        tts.shutdown()
        super.onCleared()
    }
}
