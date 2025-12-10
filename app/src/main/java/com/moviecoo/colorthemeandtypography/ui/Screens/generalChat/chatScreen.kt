package com.moviecoo.colorthemeandtypography.ui.screens.generalChat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.moviecoo.colorthemeandtypography.domain.model.ChatMessage
import com.moviecoo.colorthemeandtypography.ui.screens.generalChat.viewModel.ChatViewModel

import com.moviecoo.colorthemeandtypography.ui.theme.GradientBackground
import com.moviecoo.colorthemeandtypography.ui.theme.OnPrimary
import com.moviecoo.colorthemeandtypography.ui.theme.Primary
import com.moviecoo.colorthemeandtypography.ui.theme.Surface

/**
 * Main composable function for the General Chat Screen.
 * Uses custom colors (Primary, OnPrimary, Surface) for the theme.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    navController: NavController,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val messages by viewModel.messages.collectAsState()
    val listState = rememberLazyListState()
    var messageInput by remember { mutableStateOf("") }

    // Side effect to automatically scroll to the latest message
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.lastIndex)
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(GradientBackground), // تطبيق الخلفية المتدرجة
        containerColor = Color.Transparent, // لجعل الخلفية المتدرجة مرئية

        topBar = {
            GeneralChatTopBar(navController = navController)
        },

        bottomBar = {
            MessageInputBar(
                value = messageInput,
                onValueChange = { messageInput = it },
                onSendClick = {
                    if (messageInput.isNotBlank()) {
                        val currentUserId = "user_001"
                        val currentUserName = "User A"
                        viewModel.sendMessage(messageInput, currentUserName, currentUserId)
                        messageInput = ""
                    }
                }
            )
        }
    ) { paddingValues ->
        // Message list area
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            state = listState,
            contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(messages) { message ->
                MessageBubble(
                    message = message,
                    currentUserId = "user_001"
                )
            }
        }
    }
}


/**
 * Top App Bar using custom colors.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GeneralChatTopBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                "General Chat",
                color = OnPrimary,
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = OnPrimary
                )
            }
        },
      
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

/**
 * Input bar using custom Surface and Primary colors.
 */
@Composable
private fun MessageInputBar(
    value: String,
    onValueChange: (String) -> Unit,
    onSendClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Surface // خلفية الشريط الكلية
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = {
                    Text(
                        "Write your message...",
                        color = OnPrimary.copy(alpha = 0.6f)
                    )
                },
                singleLine = false,
                shape = RoundedCornerShape(24.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    // جعل لون خلفية حقل النص مختلفاً قليلاً عن خلفية الشريط
                    focusedContainerColor = Surface.copy(alpha = 0.9f),
                    unfocusedContainerColor = Surface.copy(alpha = 0.9f),
                    cursorColor = Primary,
                    focusedTextColor = OnPrimary,
                    unfocusedTextColor = OnPrimary,
                ),
                modifier = Modifier.weight(1f)
            )

            Spacer(Modifier.width(8.dp))


            IconButton(
                onClick = onSendClick,
                enabled = value.isNotBlank(),
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(if (value.isNotBlank()) Primary else Color.Gray.copy(alpha = 0.5f))
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send Message",
                    tint = OnPrimary
                )
            }
        }
    }
}

/**
 * Message bubble using custom Primary and Surface colors.
 */
@Composable
private fun MessageBubble(message: ChatMessage, currentUserId: String) {
    val isUserMessage = message.senderId == currentUserId


    val bubbleColor = if (isUserMessage) {
        Primary
    } else {
        Surface.copy(alpha = 0.8f)
    }
    val contentColor = OnPrimary

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = if (isUserMessage) Arrangement.End else Arrangement.Start
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = bubbleColor, contentColor = contentColor),
            shape = if (isUserMessage) {
                RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp, bottomEnd = 4.dp)
            } else {
                RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp, bottomStart = 4.dp)
            },
            elevation = CardDefaults.cardElevation(1.dp)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                if (!isUserMessage) {
                    Text(
                        text = message.senderName,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelMedium,
                        color = Primary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                }

                Text(
                    text = message.text,
                    style = MaterialTheme.typography.bodyLarge,
                    color = contentColor
                )
            }
        }
    }
}