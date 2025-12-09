package com.moviecoo.colorthemeandtypography.ui.Screens.settingScreen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.common_components.TopAppBar
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.FontSizeViewModel
import com.moviecoo.colorthemeandtypography.ui.theme.Primary
import com.moviecoo.colorthemeandtypography.ui.theme.Surface

@Composable
fun ReportProblemScreen(fontSizeViewModel: FontSizeViewModel) {
    val context = LocalContext.current
    val scale = fontSizeViewModel.fontScale.value
    var problemText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                true,
                backgroundColor = Surface,
                title = R.string.reportProblem,
            )
        },
        containerColor = Primary
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Card(
                modifier = Modifier.fillMaxSize(),
                colors = CardDefaults.cardColors(containerColor = Primary),
                elevation = CardDefaults.cardElevation(4.dp*scale)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp * scale)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = "Having a problem? Let us know!",
                        fontSize = 22.sp * scale,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.staatliches_regular)),
                        color = androidx.compose.ui.graphics.Color.White
                    )

                    Spacer(modifier = Modifier.height(16.dp * scale))

                    Text(
                        text = "Describe the issue you faced:",
                        fontSize = 18.sp * scale,
                        fontFamily = FontFamily(Font(R.font.staatliches_regular)),
                        color = androidx.compose.ui.graphics.Color.White
                    )

                    Spacer(modifier = Modifier.height(8.dp * scale))

                    OutlinedTextField(
                        value = problemText,
                        onValueChange = { problemText = it },
                        placeholder = { Text("Type your problem here...", color = Color.Gray) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(550.dp * scale),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.Gray,
                            cursorColor = Color.White,
                            focusedLabelColor = Color.White,
                            unfocusedLabelColor = Color.Gray,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        )

                    )

                    Spacer(modifier = Modifier.height(24.dp * scale))

                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_SENDTO).apply {
                                data = Uri.parse("mailto:asmaasayed01278591728@gmail.com")
                                putExtra(Intent.EXTRA_SUBJECT, "MovieCoo App Problem Report")
                                putExtra(Intent.EXTRA_TEXT, problemText)
                            }
                            context.startActivity(intent)
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(containerColor = androidx.compose.ui.graphics.Color(0xFF00C853))
                    ) {
                        Text(
                            text = "Submit",
                            fontSize = 18.sp * scale,
                            fontFamily = FontFamily(Font(R.font.staatliches_regular)),
                            color = androidx.compose.ui.graphics.Color.White
                        )
                    }
                }
            }
        }
    }
}
