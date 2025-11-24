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
import com.moviecoo.colorthemeandtypography.ui.theme.Primary


@Composable
fun SettingScreen() {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = { TopAppBar(showBackButton = true, title = R.string.setting) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .background(Primary)
                .verticalScroll(scrollState)
                .fillMaxSize()
                .padding(vertical = 12.dp) // padding عام من فوق وتحت
        ) {

            // ---------- Account Section ----------
            Text(
                text = "Account",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.staatliches_regular)),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            accountCard()
            Spacer(modifier = Modifier.height(24.dp))

            // ---------- General Section ----------
            Text(
                text = "General",
                color = Color.White,
                fontSize = 28.sp,
                fontFamily = FontFamily(Font(R.font.staatliches_regular)),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            generalCard()
            Spacer(modifier = Modifier.height(24.dp))

            // ---------- Support Section ----------
            Text(
                text = "Support",
                color = Color.White,
                fontSize = 28.sp,
                fontFamily = FontFamily(Font(R.font.staatliches_regular)),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            supportCard()
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun generalCard() {

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = shapes.large
    ) {
        Column(
            modifier = Modifier.padding(vertical = 6.dp)
        ) {

            // ---------- Notifications Row ----------
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Notifications",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.staatliches_regular)),
                    color = Color(0xFF505664)
                )

                Spacer(modifier = Modifier.weight(1f))

                var isChecked by remember { mutableStateOf(true) }

                Switch(
                    checked = isChecked,
                    onCheckedChange = { checked ->
                        isChecked = checked

                        if (checked) {
                            startNotificationService(context)
                        } else {
                            stopNotificationService(context)
                        }
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
                modifier = Modifier.padding(horizontal = 16.dp),
                thickness = 2.dp,
                color = Color.Gray.copy(alpha = 0.8f)
            )

            // ---------- Text Size ----------
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween // <-- يوزع المسافة تلقائي
            ) {
                Text(
                    text = "Text Size",
                    fontFamily = FontFamily(Font(R.font.staatliches_regular)),
                    fontSize = 20.sp,
                    color = Color(0xFF505664)
                )

                Text(
                    text = "Normal",
                    fontFamily = FontFamily(Font(R.font.staatliches_regular)),
                    fontSize = 16.sp,
                    color = Color(0xFF505664)
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp),
                thickness = 2.dp,
                color = Color.Gray.copy(alpha = 0.8f)
            )

            // ---------- Invite a Friend ----------
          Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Invite a Friend",
                    fontFamily = FontFamily(Font(R.font.staatliches_regular)),
                    fontSize = 20.sp,
                    color = Color(0xFF505664)

                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    painter = painterResource(id = R.drawable.right),
                    contentDescription = "Go to Dark Mode",
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

@Composable
fun supportCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = shapes.large
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Terms of Services",
                    fontFamily = FontFamily(Font(R.font.staatliches_regular)),
                    fontSize = 20.sp,
                    color = Color(0xFF505664)

                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    painter = painterResource(id = R.drawable.right),
                    contentDescription = "Go to Dark Mode",
                    tint = Color(0xFF505664),
                    modifier = Modifier.size(30.dp)
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp),
                thickness = 2.dp,
                color = Color.Gray.copy(alpha = 0.8f)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Contact Us",
                    fontFamily = FontFamily(Font(R.font.staatliches_regular)),
                    fontSize = 20.sp,
                    color = Color(0xFF505664)

                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    painter = painterResource(id = R.drawable.right),
                    contentDescription = "Go to Dark Mode",
                    tint =Color(0xFF505664),
                    modifier = Modifier.size(30.dp)
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp),
                thickness = 2.dp,
                color = Color.Gray.copy(alpha = 0.8f)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Report a Problem",
                    fontFamily = FontFamily(Font(R.font.staatliches_regular)),
                    fontSize = 20.sp,
                    color = Color(0xFF505664)
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    painter = painterResource(id = R.drawable.right),
                    contentDescription = "Go to Dark Mode",
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun accountCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = shapes.large
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.padding(horizontal = 6.dp))

            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = "Welcome, Riju Basu",
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }

            Spacer(Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.exit_icon),
                contentDescription = "Exit to App",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
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

@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    SettingScreen()
}
