package com.moviecoo.colorthemeandtypography.ui.Screens.settingScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.common_components.TopAppBar
import com.moviecoo.colorthemeandtypography.ui.theme.Primary
import com.moviecoo.colorthemeandtypography.ui.theme.Surface

@Composable
fun TermsScreenWithCard(scale: Float) {
    Scaffold(
        topBar = {
            TopAppBar(
                true,
                backgroundColor = Surface,
                title = R.string.termsOfMoviecoo,
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
                modifier = Modifier
                    .fillMaxSize(), // الكارد ياخد الصفحة كلها
                colors = CardDefaults.cardColors(containerColor = Primary),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp * scale)
                        .verticalScroll(rememberScrollState()), // لو النص طويل يبقى Scrollable
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = "Terms of Service (Coming Soon)",
                        fontSize = 24.sp * scale,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.staatliches_regular)),
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(16.dp * scale))

                    Text(
                        text = """
        Welcome to MovieCoo! 🎬

        These are placeholder terms. The full Terms of Service will be available soon. 
        Thank you for your patience.

        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. 
        Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. 
        Duis sagittis ipsum. Praesent mauris. Fusce nec tellus sed augue semper porta. 
        Mauris massa. Vestibulum lacinia arcu eget nulla. Class aptent taciti sociosqu ad litora 
        torquent per conubia nostra, per inceptos himenaeos. Curabitur sodales ligula in libero. 
        Sed dignissim lacinia nunc. Curabitur tortor. Pellentesque nibh. Aenean quam. In scelerisque 
        sem at dolor. Maecenas mattis. Sed convallis tristique sem. Proin ut ligula vel nunc egestas 
        porttitor. Morbi lectus risus, iaculis vel, suscipit quis, luctus non, massa. Fusce ac turpis 
        quis ligula lacinia aliquet. Mauris ipsum. Nulla metus metus, ullamcorper vel, tincidunt sed, 
        euismod in, nibh. Quisque volutpat condimentum velit. Class aptent taciti sociosqu ad litora 
        torquent per conubia nostra, per inceptos himenaeos. Nam nec ante. Sed lacinia, urna non 
        tincidunt mattis, tortor neque adipiscing diam, a cursus ipsum ante quis turpis. Nulla facilisi. 
        Ut fringilla. Suspendisse potenti. Nunc feugiat mi a tellus consequat imperdiet. Vestibulum sapien. 
        Proin quam. Etiam ultrices. Suspendisse in justo eu magna luctus suscipit. Sed lectus. Integer 
        euismod lacus luctus magna. Quisque cursus, metus vitae pharetra auctor, sem massa mattis sem, 
        at interdum magna augue eget diam. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices 
        posuere cubilia Curae; Morbi lacinia molestie dui. Praesent blandit dolor. Sed non quam. 
        In vel mi sit amet augue congue elementum. Morbi in ipsum sit amet pede facilisis laoreet.
    """.trimIndent(),
                        fontSize = 18.sp * scale,
                        fontFamily = FontFamily(Font(R.font.staatliches_regular)),
                        color = Color.White
                    )

                }
            }
        }
    }
}
