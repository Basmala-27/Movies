package com.moviecoo.colorthemeandtypography.ui.screens.geminiAssist

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.geminiAssist.viewModel.AssistantViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.moodToMovieScreen.moodToMovieViweModel.LightGrayText
import com.moviecoo.colorthemeandtypography.ui.screens.moodToMovieScreen.moodToMovieViweModel.TopBarIcons
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.LocalFontScale
import com.moviecoo.colorthemeandtypography.ui.theme.OnPrimary
import com.moviecoo.colorthemeandtypography.ui.theme.OrangeAccent
import com.moviecoo.colorthemeandtypography.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)

data class ConversationMessage(
    val id: Long = System.currentTimeMillis(),
    val text: String,
    val isUser: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssistantScreen(
    viewModel: AssistantViewModel = hiltViewModel(),
    onVoiceInputClicked: (vm: AssistantViewModel) -> Unit
) {
    val aiResponse by viewModel.aiResponse.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    var currentPrompt by remember { mutableStateOf("") }

    // Track conversation history locally for a conversational look
    val messages = remember { mutableStateListOf<ConversationMessage>() }
    val listState = rememberLazyListState()

    // --- EFFECT: Update history when AI response changes ---
    // Runs when aiResponse changes AND we are not loading
    LaunchedEffect(aiResponse) {
        if (aiResponse != "Thinking..." && aiResponse != "Hello! I'm your Movie Assistant. How can I help you?") {
            messages.add(ConversationMessage(text = aiResponse, isUser = false))
            listState.animateScrollToItem(messages.lastIndex)
        } else if (messages.isEmpty()) {
            // Add initial welcome message only once
            messages.add(ConversationMessage(text = aiResponse, isUser = false))
        }
    }

    // --- Function to handle sending a prompt ---
    val handleSend = {
        if (currentPrompt.isNotBlank()) {
            val userMessage = currentPrompt
            messages.add(ConversationMessage(text = userMessage, isUser = true))
            viewModel.sendPrompt(userMessage) // Send to AI
            currentPrompt = ""
        }
    }

    Scaffold { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Primary)


        ) {
            Row {
                val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
                IconButton(onClick = { backDispatcher?.onBackPressed() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp )
                    )
                }
            }

            Text(
                text = "How I Can Help You?",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp ,
                modifier = Modifier.padding(
                    top = 8.dp ,
                    bottom = 4.dp,
                    start = 10.dp
                )
            )
            Text(
                text = "Your Gemini Assistant is ready to assist you.",
                color = LightGrayText,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp ,
                modifier = Modifier.padding(bottom = 24.dp , start = 10.dp )
            )

            // --- Conversation Feed ---
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                state = listState,
                contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.Top
            ) {
                items(messages) { message ->
                    MessageBubble(message = message, isLoading = isLoading && !message.isUser && message == messages.last())
                }
            }

            // --- Input and Loading Area ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.4f)) // Slightly opaque dark footer
            ) {

                // --- Loading Indicator ---
                AnimatedVisibility(
                    visible = isLoading,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth(),
                        color = OrangeAccent,
                        trackColor = Color.Transparent // Minimalist track
                    )
                }

                InputBar(
                    prompt = currentPrompt,
                    onPromptChange = { currentPrompt = it },
                    onSend = handleSend,
                    onVoiceClick = { onVoiceInputClicked(viewModel) },
                    isLoading = isLoading,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
        }
    }
}


// ----------------------------------------------------
// --- Helper Composables for Design ---
// ----------------------------------------------------

@Composable
fun MessageBubble(message: ConversationMessage, isLoading: Boolean) {
    val horizontalAlignment = if (message.isUser) Alignment.End else Alignment.Start

    val aiCardColor = Color(0xFF2E3440) // Light Blue-Gray (or use your intended OnPrimary, but defined here for safety)
    val aiTextColor = Color.White

    // User
    val userCardColor = OrangeAccent.copy(alpha = 0.8f)
    val userTextColor = Color.Black // Dark text on light/colored background

    val cardColor = if (message.isUser) userCardColor else aiCardColor
    val textColor = if (message.isUser) userTextColor else aiTextColor

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalAlignment = horizontalAlignment
    ) {
        Card(
            modifier = Modifier
                .widthIn(max = 300.dp)
                .clip(RoundedCornerShape(18.dp)),
            colors = CardDefaults.cardColors(containerColor = cardColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {

                if (!message.isUser) {
                    Text(
                        "Gemini",
                        fontWeight = FontWeight.Bold,
                        color = Color.White.copy(alpha = 0.7f),
                        style = MaterialTheme.typography.labelSmall
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                }

                Text(
                    text = message.text,
                    color = textColor, // <-- This is the key line
                    fontWeight = if (!message.isUser) FontWeight.Medium else FontWeight.Normal
                )
            }
        }
    }
}
@Composable
fun InputBar(
    prompt: String,
    onPromptChange: (String) -> Unit,
    onSend: () -> Unit,
    onVoiceClick: () -> Unit,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {



    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .background(Color.White.copy(alpha = 0.05f)), // Subtle background for input field
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = prompt,
            onValueChange = onPromptChange,
            placeholder = { Text("Ask about movies...", color = Color.Gray) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = OrangeAccent,

                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White

            ),
            singleLine = true,
            modifier = Modifier.weight(1f).padding(start = 16.dp, end = 8.dp),
            enabled = !isLoading
        )

        // Voice Button
        IconButton(
            onClick = onVoiceClick,
            enabled = !isLoading
        ) {
            Icon(
                Icons.Filled.Mic,
                contentDescription = "Voice Input",
                tint = if (isLoading) Color.Gray else OrangeAccent
            )
        }

        // Send Button
        IconButton(
            onClick = onSend,
            enabled = !isLoading && prompt.isNotBlank()
        ) {
            Icon(
                Icons.Filled.Send,
                contentDescription = "Send",
                tint = if (!isLoading && prompt.isNotBlank()) OrangeAccent else Color.Gray
            )
        }
    }
}