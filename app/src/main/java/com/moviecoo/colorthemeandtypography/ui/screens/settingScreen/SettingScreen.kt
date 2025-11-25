package com.moviecoo.colorthemeandtypography.ui.screens.settingScreen

import android.R.attr.font
import android.content.Context
import android.content.Intent
import android.inputmethodservice.Keyboard
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.common_components.TopAppBar
import com.moviecoo.colorthemeandtypography.services.notification.NotificationService
import com.moviecoo.colorthemeandtypography.ui.Screens.signInScreen.fontSizeViewModel.FontSizeViewModel
import com.moviecoo.colorthemeandtypography.ui.Screens.signInScreen.fontSizeViewModel.LocalFontScale
import com.moviecoo.colorthemeandtypography.ui.theme.Primary


@Composable
fun SettingScreen(fontSizeViewModel: FontSizeViewModel) {
    val scrollState = rememberScrollState()
    val scale = fontSizeViewModel.fontScale.value
    Scaffold(
        topBar = { TopAppBar(showBackButton = true, title = R.string.setting) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .verticalScroll(scrollState)
                .fillMaxSize()
                .background(Primary)
                .padding(vertical = 12.dp * scale)
        ) {
            // ---------- Account Section ----------
            Text(
                text = "Account",
                color = Color.White,
                fontSize = 28.sp * scale,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.staatliches_regular)),
                modifier = Modifier.padding(horizontal = 16.dp * scale)
            )
            Spacer(modifier = Modifier.height(12.dp * scale))
            accountCard(fontSizeViewModel)

            Spacer(modifier = Modifier.height(24.dp * scale))

            // ---------- General Section ----------
            Text(
                text = "General",
                color = Color.White,
                fontSize = 28.sp * scale,
                fontFamily = FontFamily(Font(R.font.staatliches_regular)),
                modifier = Modifier.padding(horizontal = 16.dp * scale)
            )
            Spacer(modifier = Modifier.height(12.dp * scale))
            generalCard(fontSizeViewModel, scale)
            Spacer(modifier = Modifier.height(24.dp * scale))

            // ---------- Support Section ----------
            Text(
                text = "Support",
                color = Color.White,
                fontSize = 28.sp * scale,
                fontFamily = FontFamily(Font(R.font.staatliches_regular)),
                modifier = Modifier.padding(horizontal = 16.dp * scale)
            )
            Spacer(modifier = Modifier.height(12.dp * scale))
            supportCard(fontSizeViewModel) // ✅ صح

            Spacer(modifier = Modifier.height(24.dp * scale))
        }
    }
}


@Composable
fun generalCard(fontSizeViewModel: FontSizeViewModel, scale: Float) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp * scale),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = shapes.large
    ) {
        Column(modifier = Modifier.padding(vertical = 6.dp * scale)) {

            // ---------- Notifications Row ----------
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp * scale, vertical = 8.dp * scale),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Notifications",
                    fontSize = 20.sp * scale,
                    fontFamily = FontFamily(Font(R.font.staatliches_regular)),
                    color = Color(0xFF505664)
                )

                Spacer(modifier = Modifier.weight(1f))

                var isChecked by remember { mutableStateOf(true) }

                Switch(
                    checked = isChecked,
                    onCheckedChange = { checked ->
                        isChecked = checked
                        if (checked) startNotificationService(context)
                        else stopNotificationService(context)
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = Color(0xFF09274C),
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color.Gray
                    )
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp * scale),
                thickness = 2.dp * scale,
                color = Color.Gray.copy(alpha = 0.8f)
            )

            // ---------- Text Size Row + Slider ----------
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp * scale, vertical = 16.dp * scale)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Text Size",
                        fontFamily = FontFamily(Font(R.font.staatliches_regular)),
                        fontSize = 20.sp * scale,
                        color = Color(0xFF505664)
                    )

                    Text(
                        text = "${(fontSizeViewModel.fontScale.value * 100).toInt()}%",
                        fontFamily = FontFamily(Font(R.font.staatliches_regular)),
                        fontSize = 16.sp * scale,
                        color = Color(0xFF505664)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp * scale))

                Slider(
                    value = fontSizeViewModel.fontScale.value,
                    valueRange = 0.8f..1.5f,
                    onValueChange = { fontSizeViewModel.fontScale.value = it }
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp * scale),
                thickness = 2.dp * scale,
                color = Color.Gray.copy(alpha = 0.8f)
            )

            // ---------- Invite a Friend ----------
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp * scale, vertical = 16.dp * scale),
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
                    contentDescription = "Go to Dark Mode",
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp * scale)
                )
            }
        }
    }
}




@Composable
fun supportCard(fontSizeViewModel: FontSizeViewModel) {
    val scale = fontSizeViewModel.fontScale.value

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp * scale),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = shapes.large
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp * scale)) {

            val items = listOf("Terms of Services", "Contact Us", "Report a Problem")
            val colors = listOf(Color(0xFF505664), Color(0xFF505664), Color.Black)

            items.forEachIndexed { index, text ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp * scale, vertical = 16.dp * scale),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = text,
                        fontFamily = FontFamily(Font(R.font.staatliches_regular)),
                        fontSize = 20.sp * scale,
                        color = colors[index]
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Icon(
                        painter = painterResource(id = R.drawable.right),
                        contentDescription = null,
                        tint = colors[index],
                        modifier = Modifier.size(30.dp * scale)
                    )
                }

                if (index != items.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp * scale),
                        thickness = 2.dp * scale,
                        color = Color.Gray.copy(alpha = 0.8f)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}




@Composable
fun accountCard(fontSizeViewModel: FontSizeViewModel) {
    val scale = fontSizeViewModel.fontScale.value

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp * scale)
            .padding(10.dp * scale),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = shapes.large
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp * scale),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp * scale)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(6.dp * scale))

            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = "Welcome, Riju Basu",
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 18.sp * scale,
                    color = Color.Black
                )
            }

            Spacer(Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.exit_icon),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(24.dp * scale)
            )
        }
    }
}


// ---------- Service Functions ----------
fun startNotificationService(context: Context) {
    val intent = Intent(context, NotificationService::class.java)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        context.startForegroundService(intent)
    } else {
        context.startService(intent)
    }
    Toast.makeText(context, "Notification Started", Toast.LENGTH_SHORT).show()

}

fun stopNotificationService(context: Context) {
    val intent = Intent(context, NotificationService::class.java)
    context.stopService(intent)
    Toast.makeText(context, "Notification Stopped", Toast.LENGTH_SHORT).show()
}


