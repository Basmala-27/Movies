package com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkAdded
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.common_components.TopAppBar
import com.moviecoo.colorthemeandtypography.mapper.toMovieUiModel
import com.moviecoo.colorthemeandtypography.ui.Screens.WatchListScreen.component.WatchlistStorage
import com.moviecoo.colorthemeandtypography.ui.Screens.favoriteScreen.FavoriteStorage
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.FontSizeViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.data.MovieDetailsUiModel
import com.moviecoo.colorthemeandtypography.ui.theme.UserAccount


@Composable
fun MovieDetailsUiScreen(movie: MovieDetailsUiModel, fontSizeViewModel: FontSizeViewModel , navController: NavController) {
    val scale = fontSizeViewModel.fontScale.value
    val detailsViewModel: MovieDetailsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    val cast by detailsViewModel.cast.collectAsState()
    val context = LocalContext.current
    var showFullDescription by remember { mutableStateOf(false) }
    var isSaved by remember { mutableStateOf(false) }
    var isFavorite by remember { mutableStateOf(false) }
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    LaunchedEffect(movie.id) {
        isSaved = WatchlistStorage.isSaved(context, movie.id)
        isFavorite = FavoriteStorage.isSaved(context, movie.id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // ← Scroll يشمل الصورة
            .background(Color(0xFF061E3B))
    ) {
        // ← السهم الخلفي
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            // الصورة
            AsyncImage(
                model = movie.image,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // gradient overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color(0xC0061E3B)),
                            startY = 300f * scale,
                            endY = 1200f * scale
                        )
                    )
            )

            // السهم فوق الصورة
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp * scale),
                verticalAlignment = Alignment.Top
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp * scale)
                    )
                }
            }
        }

        // محتوى الفيلم مع Scroll
        Column(
            modifier = Modifier
                .weight(2f / 3f)
                .fillMaxWidth()
                .padding(horizontal = 20.dp * scale)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp * scale)
        ) {
            // عنوان الفيلم
            Text(
                text = movie.title,
                color = Color.White,
                fontSize = 28.sp * scale,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.roboto_regular))
            )

            // السنة والتقييم
            Text(
                text = "${movie.year} || ${movie.rating} +min",
                color = UserAccount,
                fontSize = 18.sp * scale,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            // وصف الفيلم 3 أسطر فقط
            Text(
                text = movie.overview,
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

            // Cast
            Text(
                text = "Cast",
                color = Color.White,
                fontSize = 25.sp * scale,
                fontWeight = FontWeight.Bold
            )

            LazyRow(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                items(cast) { actor ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        AsyncImage(
                            model = actor.imageUrl,
                            contentDescription = actor.name,
                            modifier = Modifier
                                .size(40.dp * scale)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )

                    }
                }
            }

            // Row الأزرار (الحفظ والقلب + Watch Now)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp * scale, vertical = 16.dp * scale),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // قلب للحفظ
                // قلب للحفظ في المفضلات
                IconButton(
                    onClick = {
                        if (isFavorite) {
                            FavoriteStorage.removeMovie(context, movie.id)
                        } else {
                            FavoriteStorage.saveMovie(context, movie.toMovieUiModel())
                        }
                        isFavorite = !isFavorite
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color.Red else Color.White,
                        modifier = Modifier.size(30.dp * scale)
                    )
                }

                // علامة الحفظ (مثلاً Bookmark)
                IconButton(
                    onClick = {
                    if (isSaved) WatchlistStorage.removeMovie(context, movie.id)
                    else WatchlistStorage.saveMovie(context, movie)
                    isSaved = !isSaved })
                {
                    Icon(
                        imageVector = if (isSaved) Icons.Default.BookmarkAdded else Icons.Default.BookmarkBorder,
                        contentDescription = null,
                        tint = if (isSaved) Color(0xFFFFD700) else Color.White)
                }
                // زرار Watch Now
                Button(
                    onClick = { navController.navigate("movie_content/${movie.id}") },
                    modifier = Modifier
                        .height(50.dp * scale)
                        .weight(1f) // ياخد باقي المساحة
                        .padding(start = 8.dp * scale),
                    shape = RoundedCornerShape(16.dp * scale),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEC255A))
                ) {
                    Text(
                        text = "Watch Now",
                        color = Color.White,
                        fontSize = 16.sp * scale,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }
    }
}

