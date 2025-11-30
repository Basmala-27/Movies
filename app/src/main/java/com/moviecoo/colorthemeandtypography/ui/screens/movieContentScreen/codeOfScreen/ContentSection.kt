package com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.codeOfScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.common_components.RatingRow
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.MovieDetailsViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.FontSizeViewModel

import com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.data.MovieContentData
import com.moviecoo.colorthemeandtypography.ui.theme.UserAccount
import kotlinx.coroutines.launch

@Composable
fun ContentSection(
    title: String,
    rating: Double,
    year: String,
    genre: String,
    duration: String,
    description: String,
    staticData: MovieContentData, // Supplies the static cast/upnext lists
    fontSizeViewModel: FontSizeViewModel,
    detailsViewModel: MovieDetailsViewModel ,
    navController: NavController  // ← هنا أضفتي NavController// <--- هنا
) {
    val scale = fontSizeViewModel.fontScale.value
   // val detailsViewModel: MovieDetailsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    val cast by detailsViewModel.cast.collectAsState()
    val context = LocalContext.current
    val similarMovies by detailsViewModel.similarMovies.collectAsState()
    var showFullDescription by remember { mutableStateOf(false) }
    Column(modifier = Modifier.padding(16.dp * scale)) {

        // 💡 DYNAMIC DATA
        Text(
            text = title,
            color = Color.White,
            fontSize = 32.sp * scale,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily(Font(R.font.roboto_regular))
        )
        Spacer(modifier = Modifier.height(12.dp * scale))

        // Rating
        // NOTE: Assuming RatingRow takes a Float or Double
        RatingRow(rating)
        Spacer(modifier = Modifier.height(8.dp * scale))

        // Movie Info
        // 💡 DYNAMIC DATA
        Text(
            text = "$year | $genre | $duration",
            color = UserAccount,
            fontSize = 18.sp * scale
        )
        Spacer(modifier = Modifier.height(16.dp * scale))

        // Description
        // 💡 DYNAMIC DATA
        Text(
            text = description ,
            color = UserAccount,
            fontSize = 16.sp * scale,
            maxLines = if (showFullDescription) Int.MAX_VALUE else 3,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = if (showFullDescription) "Show Less" else "Read More",
            color = Color(0xFFEC255A),
            fontSize = 14.sp * scale,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clickable { showFullDescription = !showFullDescription }
                .padding(top = 4.dp)
        )
        // Cast Title
        Text(
            text = "Cast",
            color = Color.White,
            fontSize = 24.sp * scale,
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
            fontWeight = FontWeight.Bold
        )

        LazyRow(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
            items(cast) { actor ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    AsyncImage(
                        model = actor.imageUrl , // أي صورة placeholder عندك
                        contentDescription = actor.name,
                        modifier = Modifier
                            .size(40.dp * scale)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp * scale))

        // Up Next Title
        Text(
            text = "Up Next",
            color = Color.White,
            fontSize = 24.sp * scale,
            fontFamily = FontFamily(Font(R.font.roboto_bold)),
            fontWeight = FontWeight.Bold
        )
        val listState = rememberLazyListState() // 1. نحتفظ بالـ state للـ LazyRow
        val coroutineScope = rememberCoroutineScope() // 2. coroutine scope عشان animation
        // Up Next List (STATIC DATA)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp * scale)
        ) {
            LazyRow(
                state = listState,
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(end = 50.dp * scale)
            ) {
                items(similarMovies) { movie ->
                    Column(
                        modifier = Modifier.padding(end = 12.dp * scale),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500${movie.posterUrl}",
                            contentDescription = movie.title1,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(110.dp * scale)
                                .height(165.dp * scale)
                                .clip(RoundedCornerShape(8.dp * scale))
                                .clickable {
                                    navController.navigate("movie_details/${movie.id}")  // ← هنا ID الديناميكي
                                }
                        )
                        Spacer(modifier = Modifier.height(8.dp * scale))

                    }
                }
            }
            // Scroll Icon (omitted for brevity, but stays here)

            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(40.dp * scale)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.3f),
                                Color.White.copy(alpha = 0.1f)
                            )
                        )
                    )
                .clickable {
                coroutineScope.launch {
                    val nextIndex = (listState.firstVisibleItemIndex + 1).coerceAtMost(similarMovies.size - 1)
                    listState.animateScrollToItem(nextIndex)
                }
            },
            contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    contentDescription = "Scroll next",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp * scale)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp * scale))
    }
}