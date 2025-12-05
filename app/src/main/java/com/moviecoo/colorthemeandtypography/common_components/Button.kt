package com.moviecoo.colorthemeandtypography.common_components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import com.moviecoo.colorthemeandtypography.R
import kotlinx.coroutines.launch

@Composable
fun AuthButton(
    buttonText: String,
    email: String,
    password: String,
    width: Dp = 351.dp,
    height: Dp = 57.dp,
    onClickAction: suspend () -> Boolean
) {
    val context = LocalContext.current
    val isEnabled = email.isNotBlank() && password.isNotBlank()

    val scope = rememberCoroutineScope()

    Button(
        onClick = {
            if (!isEnabled) return@Button
            scope.launch {
                onClickAction()
            }
        },
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF0E3E62),
            disabledContainerColor = Color(0xFF0E3E62).copy(alpha = 0.5f)
        ),
        shape = RoundedCornerShape(13.dp),
        modifier = Modifier
            .width(width)
            .height(height)
    ) {
        Text(
            text = buttonText,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily(Font(R.font.inter_medium)),
            style = TextStyle(
                shadow = Shadow(
                    color = Color.White,
                    offset = Offset(0f, 0f),
                    blurRadius = 6f
                )
            )
        )
    }
}


