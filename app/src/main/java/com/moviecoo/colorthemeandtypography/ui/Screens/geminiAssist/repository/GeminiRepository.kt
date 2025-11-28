package com.moviecoo.colorthemeandtypography.ui.screens.geminiAssist.repository
import com.moviecoo.colorthemeandtypography.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeminiRepository @Inject constructor() {

    // Initialize the Generative Model
    private val generativeModel = GenerativeModel(
        modelName = "gemini-2.5-flash", // Use modelName parameter
        apiKey = BuildConfig.GEMINI_API_KEY
    )

    suspend fun generateContent(prompt: String): String {
        return try {
            val response = generativeModel.generateContent(prompt)

            response.text ?: "I did not receive a valid response from the AI."
        } catch (e: Exception) {
            "Error: Failed to connect to AI. Check Internet or API key. Details: ${e.message}"
        }
    }
}