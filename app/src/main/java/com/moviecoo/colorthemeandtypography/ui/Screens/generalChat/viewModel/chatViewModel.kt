package com.moviecoo.colorthemeandtypography.ui.screens.generalChat.viewModel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviecoo.colorthemeandtypography.domain.model.ChatMessage
import com.moviecoo.colorthemeandtypography.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository,

) : ViewModel() {

    val messages: StateFlow<List<ChatMessage>> = chatRepository.getMessages()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun sendMessage(text: String, currentUserName: String, currentUserId: String) {
        if (text.isBlank()) return

        val message = ChatMessage(
            senderId = currentUserId,
            senderName = currentUserName,
            text = text,
            timestamp = System.currentTimeMillis()
        )

        viewModelScope.launch {
            chatRepository.sendMessage(message)
        }
    }
}