package com.moviecoo.colorthemeandtypography.domain.repository

import com.moviecoo.colorthemeandtypography.domain.model.ChatMessage
import kotlinx.coroutines.flow.Flow

interface ChatRepository{

    fun getMessages(): Flow<List<ChatMessage>>


    suspend fun sendMessage(message: ChatMessage)

}