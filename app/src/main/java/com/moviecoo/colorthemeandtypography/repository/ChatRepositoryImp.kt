package com.moviecoo.colorthemeandtypography.repository

//import android.app.DownloadManager
import com.google.firebase.firestore.FirebaseFirestore
import com.moviecoo.colorthemeandtypography.domain.model.ChatMessage
import com.moviecoo.colorthemeandtypography.domain.repository.ChatRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import com.google.firebase.firestore.Query


class ChatRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : ChatRepository {

    private val chatCollection = firestore.collection("general_chat")


    override fun getMessages(): Flow<List<ChatMessage>> = callbackFlow {


        val subscription = chatCollection
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .limit(100)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                if (snapshot != null) {

                    val messages = snapshot.documents.map { doc ->
                        doc.toObject(ChatMessage::class.java)!!.copy(id = doc.id)
                    }
                    trySend(messages)
                }
            }


        awaitClose { subscription.remove() }
    }

    override suspend fun sendMessage(message: ChatMessage) {

        chatCollection.add(message).await()
    }
}