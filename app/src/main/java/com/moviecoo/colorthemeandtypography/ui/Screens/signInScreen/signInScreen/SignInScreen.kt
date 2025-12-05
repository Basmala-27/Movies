package com.moviecoo.colorthemeandtypography.ui.Screens.signInScreen.signInScreen


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import com.moviecoo.colorthemeandtypography.R

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.moviecoo.colorthemeandtypography.common_components.AppNameAnimated
import com.moviecoo.colorthemeandtypography.common_components.AuthButton
import com.moviecoo.colorthemeandtypography.common_components.CustomOutlinedTextField
import com.moviecoo.colorthemeandtypography.common_components.LoadingOverlay
import com.moviecoo.colorthemeandtypography.ui.theme.OnPrimary
import kotlinx.coroutines.suspendCancellableCoroutine


@Composable
fun SignInScreen(
    onSignInClick: (String, String) -> Unit = { _, _ -> },
    onSignUpClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoadingScreen by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    Scaffold { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {

            // Background image
            Image(
                painter = painterResource(R.drawable.signinsignupscreenbackgound),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // App Name
            AppNameAnimated(
                modifier = Modifier.absoluteOffset(x = 88.dp, y = 240.dp),
                enablePulse = true
            )
            // Form
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.weight(1.5f))

                CustomOutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    labelText = "Email"
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomOutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    labelText = "Password",
                    isPassword = true
                )
                Spacer(modifier = Modifier.height(20.dp))
                AuthButton(
                    buttonText = "Sign In",
                    email = email,
                    password = password
                ) {
                    isLoadingScreen = true
                    try {
                        suspendCancellableCoroutine<Boolean> { cont ->
                            auth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    isLoadingScreen = false
                                    if (task.isSuccessful) {
                                        Toast.makeText(context, "Welcome back!", Toast.LENGTH_SHORT).show()
                                        onSignInClick(email, password)
                                        cont.resume(true) {}
                                    } else {
                                        val errorMessage = when (task.exception) {
                                            is FirebaseAuthInvalidUserException -> "User not found. Please sign up first."
                                            is FirebaseAuthInvalidCredentialsException -> "Incorrect email or password."
                                            else -> "Something went wrong."
                                        }
                                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                        cont.resume(false) {}
                                    }
                                }
                        }
                    } catch (e: Exception) {
                        isLoadingScreen = false
                        Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show()
                        false
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Don't have an account? ",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    TextButton(onClick = { onSignUpClick() }) {
                        Text(
                            text = "Sign Up",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 14.sp
                        )
                    }
                }
            }
            if (isLoadingScreen) {
                LoadingOverlay(
                    overlayAlpha = 0.5f,
                    indicatorStroke = 3f,
                    indicatorSize = 64
                )
            }

        }
    }
}





