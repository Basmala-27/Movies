package com.moviecoo.colorthemeandtypography.common_components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import com.moviecoo.colorthemeandtypography.R
import kotlinx.coroutines.launch

/**
 * ## AuthButton
 * A reusable composable button designed for authentication screens (Login/Register).
 *
 * It provides **automatic enabling/disabling** based on input fields and manages a
 * **loading state** by displaying a [CircularProgressIndicator] during the execution
 * of the asynchronous authentication action.
 *
 * @param buttonText The text to display on the button (e.g., "Login" or "Sign Up").
 * @param email The current value of the email input field (used for enabling check).
 * @param password The current value of the password input field (used for enabling check).
 * @param width The width of the button.
 * @param height The height of the button.
 * @param onClickAction A suspend function that executes the core authentication logic (e.g., network call).
 * It returns a Boolean result but is executed for its side effects.
 */
@Composable
fun AuthButton(
    buttonText: String,
    email: String,
    password: String,
    width: Dp = 351.dp,
    height: Dp = 57.dp,
    onClickAction: suspend () -> Boolean
) {
    // 1. State Management
    // Determines if input fields are valid to enable the button.
    val isInputValid = email.isNotBlank() && password.isNotBlank()

    // Tracks the loading state for showing the spinner and disabling user input.
    var isLoading by remember { mutableStateOf(false) }

    // Creates a CoroutineScope tied to this composable's lifecycle for launching suspend functions.
    val scope = rememberCoroutineScope()

    // Determines the final enabled state: only enabled if inputs are valid AND not currently loading.
    val isEnabled = isInputValid && !isLoading

    // 2. Button Composable
    Button(
        onClick = {
            // Guard clause: Prevents action if the button is already disabled or loading.
            if (!isEnabled) return@Button

            // Start the loading state before the asynchronous task begins.
            isLoading = true

            // Launch the suspend function within the CoroutineScope.
            scope.launch {
                try {
                    // Execute the core authentication logic.
                    onClickAction()
                } finally {
                    // Ensure the loading state is reset regardless of success or failure (try-finally block).
                    isLoading = false
                }
            }
        },
        enabled = isEnabled, // Controls