//package com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.codeOfScreen
//
//import android.media.browse.MediaBrowser
//import android.net.Uri
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Fullscreen
//import androidx.compose.material.icons.filled.KeyboardArrowDown
//import androidx.compose.material.icons.filled.PlayArrow
//import androidx.compose.material.icons.filled.SkipNext
//import androidx.compose.material.icons.filled.SkipPrevious
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Slider
//import androidx.compose.material3.SliderDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.moviecoo.colorthemeandtypography.ui.Screens.signInScreen.fontSizeViewModel.FontSizeViewModel
//import com.moviecoo.colorthemeandtypography.ui.Screens.signInScreen.fontSizeViewModel.LocalFontScale
//import com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.data.sampleMovie
//
//@Composable
//fun VideoPlayerSection(thumbnailRes: Int,fontSizeViewModel: FontSizeViewModel , onClose: () -> Unit) {
//    val scale = fontSizeViewModel.fontScale.value
//
//
//    val context = LocalContext.current
//
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(210.dp * scale)
//    ) {
//        // Thumbnail
//        Image(
//            painter = painterResource(id = thumbnailRes),
//            contentDescription = "Video Thumbnail",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.fillMaxSize()
//        )
//
//        // Close Icon
//        Icon(
//            imageVector = Icons.Filled.KeyboardArrowDown,
//            contentDescription = "Close",
//            tint = Color.White,
//            modifier = Modifier
//                .align(Alignment.TopStart)
//                .padding(16.dp * scale)
//                .size(28.dp * scale)
//                .clickable{onClose}
//        )
//
//        // Play / Skip Row
//        Row(
//            modifier = Modifier.align(Alignment.Center),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            // Skip Previous
//            Box(
//                modifier = Modifier
//                    .size(40.dp * scale)
//                    .shadow(
//                        elevation = 12.dp * scale,
//                        shape = CircleShape,
//                        ambientColor = Color.White.copy(alpha = 0.3f)
//                    )
//                    .clip(CircleShape)
//                    .background(
//                        Brush.radialGradient(
//                            colors = listOf(
//                                Color.White.copy(alpha = 0.3f),
//                                Color.White.copy(alpha = 0.1f)
//                            )
//                        )
//                    ),
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    imageVector = Icons.Filled.SkipPrevious,
//                    contentDescription = "Skip Previous",
//                    tint = Color.White,
//                    modifier = Modifier.size(28.dp * scale)
//                )
//            }
//
//            Spacer(modifier = Modifier.width(24.dp * scale))
//
//            // Play Button
//            Box(
//                modifier = Modifier
//                    .size(50.dp * scale)
//                    .shadow(
//                        elevation = 16.dp * scale,
//                        shape = CircleShape,
//                        ambientColor = Color.White.copy(alpha = 0.4f)
//                    )
//                    .clip(CircleShape)
//                    .background(
//                        Brush.radialGradient(
//                            colors = listOf(
//                                Color.White.copy(alpha = 0.4f),
//                                Color.White.copy(alpha = 0.15f)
//                            )
//                        )
//                    ),
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    imageVector = Icons.Filled.PlayArrow,
//                    contentDescription = "Play",
//                    tint = Color.White,
//                    modifier = Modifier.size(32.dp * scale)
//                )
//            }
//
//            Spacer(modifier = Modifier.width(24.dp * scale))
//
//            // Skip Next
//            Box(
//                modifier = Modifier
//                    .size(40.dp * scale)
//                    .shadow(
//                        elevation = 12.dp * scale,
//                        shape = CircleShape,
//                        ambientColor = Color.White.copy(alpha = 0.3f)
//                    )
//                    .clip(CircleShape)
//                    .background(
//                        Brush.radialGradient(
//                            colors = listOf(
//                                Color.White.copy(alpha = 0.3f),
//                                Color.White.copy(alpha = 0.1f)
//                            )
//                        )
//                    ),
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    imageVector = Icons.Filled.SkipNext,
//                    contentDescription = "Skip Next",
//                    tint = Color.White,
//                    modifier = Modifier.size(28.dp * scale)
//                )
//            }
//        }
//
//        // Slider + Fullscreen Row
//        Row(
//            modifier = Modifier
//                .align(Alignment.BottomCenter)
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp * scale, vertical = 12.dp * scale),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Slider(
//                value = 0.3f,
//                onValueChange = { /* TODO */ },
//                modifier = Modifier.weight(1f),
//                colors = SliderDefaults.colors(
//                    thumbColor = Color.White,
//                    activeTrackColor = Color(0xFFE50914),
//                    inactiveTrackColor = Color.White.copy(alpha = 0.3f)
//                )
//            )
//            Spacer(modifier = Modifier.width(12.dp * scale))
//            Icon(
//                imageVector = Icons.Filled.Fullscreen,
//                contentDescription = "Fullscreen",
//                tint = Color.White,
//                modifier = Modifier.size(28.dp * scale)
//            )
//        }
//    }
//}
