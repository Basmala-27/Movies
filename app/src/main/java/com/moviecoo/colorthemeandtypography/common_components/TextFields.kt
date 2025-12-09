package com.moviecoo.colorthemeandtypography.common_components

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp


/**
 * ## CustomOutlinedTextField
 * A reusable, styled [OutlinedTextField] component that supports standard text input
 * and includes built-in logic for a togglable password visibility icon.
 *
 * @param value The current text value of the field.
 * @param onValueChange The callback function triggered when the text changes.
 * @param labelText The hint/label text displayed inside the field.
 * @param modifier The modifier for this composable.
 * @param isPassword If true, enables password masking and the visibility toggle icon.
 * @param minHeight The minimum height of the text field container.
 * @param width The fixed width of the text field container.
 */
@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    minHeight: Dp = 57.dp,
    width: Dp = 351.dp
) {
    // State to manage the visibility of the password, only relevant if isPassword is true.
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            // Label text styling
            Text(labelText, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        },
        modifier = modifier
            .width(width)
            .heightIn(min = minHeight),
        shape = RoundedCornerShape(13.dp),
        textStyle = TextStyle(color = Color.White),
        singleLine = true,

        // 1. Conditional Visual Transformation
        visualTransformation = when {
            // Apply masking if it's a password field AND the visibility is turned off.
            isPassword && !passwordVisible -> PasswordVisualTransformation()
            // Otherwise, use the default visual transformation (show text normally).
            else -> VisualTransformation.None
        },

        // 2. Conditional Keyboard Type
        // Set the keyboard to password mode to suggest password-related autofill and input options.
        keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,

        // 3. Conditional Trailing Icon (Password Visibility Toggle)
        trailingIcon = {
            if (isPassword) {
                // Choose the appropriate icon based on the current visibility state.
                val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff

                Icon(
                    imageVector = image,
                    contentDescription = if (passwordVisible) "Hide password" else "Show password",
                    // Toggle the passwordVisible state on click.
                    modifier = Modifier.clickable { passwordVisible = !passwordVisible },
                    tint = Color.White
                )
            }
        }
    )
}