package com.moviecoo.colorthemeandtypography.ui.screens.geminiAssist.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviecoo.colorthemeandtypography.ui.screens.geminiAssist.repository.GeminiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssistantViewModel @Inject constructor(
    private val geminiRepository: GeminiRepository
) : ViewModel() {

    private val _aiResponse = MutableStateFlow<String>("Hello! I'm your Movie Assistant. How can I help you?")
    val aiResponse: StateFlow<String> = _aiResponse

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun sendPrompt(prompt: String) {
        if (prompt.isBlank() || _isLoading.value) return

        _isLoading.value = true
        _aiResponse.value = "Thinking..."

        viewModelScope.launch {
            val response = geminiRepository.generateContent(prompt)
            _aiResponse.value = response
            _isLoading.value = false
        }
    }
}