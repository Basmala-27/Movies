package com.moviecoo.colorthemeandtypography.ui.Screens.settingScreen

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.moviecoo.colorthemeandtypography.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.FontSizeViewModel

@Composable
fun inviteFriendRow(fontSizeViewModel: FontSizeViewModel) {
    val context = LocalContext.current
    val scale = fontSizeViewModel.fontScale.value

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp * scale, vertical = 16.dp * scale)
            .clickable {

                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "Hey! I'm enjoying MovieCoo 🎬 – discover and track your favorite movies! The app will be available soon, stay tuned!"
                    )
                    type = "text/plain"
                }
                context.startActivity(
                    Intent.createChooser(shareIntent, "Share via")
                )
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Invite a Friend",
            fontFamily = FontFamily(Font(R.font.staatliches_regular)),
            fontSize = 20.sp * scale,
            color = Color(0xFF505664)
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            painter = painterResource(id = R.drawable.right),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(30.dp * scale)
        )
    }
}
