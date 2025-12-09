package com.moviecoo.colorthemeandtypography.ui.screens.generalChat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.moviecoo.colorthemeandtypography.common_components.TopAppBar
import com.moviecoo.colorthemeandtypography.domain.model.ChatMessage
import com.moviecoo.colorthemeandtypography.ui.screens.generalChat.viewModel.ChatViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.FontSizeViewModel
import com.moviecoo.colorthemeandtypography.ui.theme.GradientBackground
import com.moviecoo.colorthemeandtypography.ui.theme.OnPrimary
import com.moviecoo.colorthemeandtypography.ui.theme.Primary
import com.moviecoo.colorthemeandtypography.ui.theme.Surface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
               navController: NavController,
               viewModel: ChatViewModel = hiltViewModel()
) {

    val messages by viewModel.messages.collectAsState()
    val listState = rememberLazyListState()
    var messageInput by remember { mutableStateOf("") }


    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.lastIndex)
        }
    }

    Scaffold(
        containerColor = Color.Transparent,
        modifier = Modifier.background(GradientBackground),


        topBar = {
         TopAppBar(
                title = { Text("General Chat", color = OnPrimary) },
                navigationIcon = {
                    androidx.compose.material3.IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = OnPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },

        bottomBar = {
            MessageInput(
                value = messageInput,
                onValueChange = { messageInput = it },
                onSendClick = {
                    val currentUserId = "user123"
                    val currentUserName = "User A"
                    viewModel.sendMessage(messageInput, currentUserName, currentUserId)
                    messageInput = ""
                }
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            state = listState,
            contentPadding = PaddingValues(top = 8.dp)
        ) {
            items(messages) { message ->
                MessageBubble(message = message, currentUserId = "user123")
            }
        }
    }
}





@Composable
fun MessageInput(
    value: String,
    onValueChange: (String) -> Unit,
    onSendClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Surface)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(
                    "Write Your Message...",
                    color = Color(0x99FFFFFF)
                )
            },

            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                cursorColor = OnPrimary,
                focusedBorderColor = Primary,
                unfocusedBorderColor = Color(0xAAFFFFFF)
            ),
            textStyle = TextStyle(color = OnPrimary),
            modifier = Modifier.weight(1f)
        )
        Spacer(Modifier.width(8.dp))
        Button(
            onClick = onSendClick,
            enabled = value.isNotBlank(),

            colors = ButtonDefaults.buttonColors(
                containerColor = Primary,
                contentColor = OnPrimary
            )
        ) {
            Text("Send")
        }
    }
}



@Composable
fun MessageBubble(message: ChatMessage, currentUserId: String) {
    val isUserMessage = message.senderId == currentUserId

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = if (isUserMessage) Arrangement.End else Arrangement.Start
    ) {
        Card(

            colors = CardDefaults.cardColors(

                containerColor = if (isUserMessage) Primary else Surface
            ),

            shape = if (isUserMessage) RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp, bottomEnd = 4.dp)
            else RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp, bottomStart = 4.dp)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                if (!isUserMessage) {
                    Text(
                        text = message.senderName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Text(text = message.text, color = OnPrimary)

            }
        }
    }
}


