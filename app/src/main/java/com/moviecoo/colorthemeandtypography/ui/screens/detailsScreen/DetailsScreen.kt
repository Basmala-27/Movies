package com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen

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
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
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
import com.moviecoo.colorthemeandtypography.mapper.toMovieUiModel
import com.moviecoo.colorthemeandtypography.ui.Screens.WatchListScreen.component.WatchlistStorage
import com.moviecoo.colorthemeandtypography.ui.Screens.favoriteScreen.FavoriteStorage
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.FontSizeViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.data.MovieDetailsUiModel
import com.moviecoo.colorthemeandtypography.ui.theme.UserAccount

/**
 * ## MovieDetailsUiScreen
 * Displays the detailed information for a single movie, including poster image,
 * overview, cast, and interactive buttons for Watchlist and Favorites.
 *
 * It uses a complex layout with weighted Columns to manage the large image header
 * and the scrollable content area.
 *
 * @param movie The [MovieDetailsUiModel] containing all data to display.
 * @param fontSizeViewModel ViewModel for fetching the current font scale for accessibility.
 * @param navController The NavController used for navigating back or to the content playback screen.
 */
@Composable
fun MovieDetailsUiScreen(
    movie: MovieDetailsUiModel,
    fontSizeViewModel: FontSizeViewModel,
    navController: NavController
) {
    val scale = fontSizeViewModel.fontScale.value
    // ViewModel instance is retrieved (using the standard Compose lifecycle aware viewModel() helper).
    val detailsViewModel: MovieDetailsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    val cast by detailsViewModel.cast.collectAsState()
    val context = LocalContext.current

    // State for controlling the expansion/collapse of the movie overview text.
    var showFullDescription by remember { mutableStateOf(false) }

    // States for tracking and toggling persistence status (Watchlist and Favorites).
    var isSaved by remember { mutableStateOf(false) }
    var isFavorite by remember { mutableStateOf(false) }

    // Load initial persistence status when the screen is first composed.
    LaunchedEffect(movie.id) {
        isSaved = WatchlistStorage.isSaved(context, movie.id)
        isFavorite = FavoriteStorage.isSaved(context, movie.id)
    }

    // --- Main Layout: Two large, scrollable, vertically weighted sections ---
    Column(
        modifier = Modifier
            .fillMaxSize()
            // The outer Column is scrollable to allow the whole view (including the image) to scroll.
            .verticalScroll(rememberScrollState())
            .background(Color(0xFF061E3B))
    ) {
        // 1. Image Header Section (Weighted to take more space initially)
        Box(
            // Weight determines the proportional height of this section.
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            // Movie Poster Image
            AsyncImage(
                model = movie.image,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Gradient Overlay (for smooth transition to background)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color(0xC0061E3B)),
                            // Start and End Y values control where the gradient is strongest.
                            startY = 300f * scale,
                            endY = 1200f * scale
                        )
                    )
            )

            // Back Button (Layered on top of the image and gradient)
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

        // 2. Movie Content Section
        Column(
            // Weight determines the proportional height of this section.
            modifier = Modifier
                .weight(2f / 3f)
                .fillMaxWidth()
                .padding(horizontal = 20.dp * scale)
                // This inner Column also needs to be scrollable if content exceeds the weighted height.
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp * scale)
        ) {
            // Movie Title
            Text(
                text = movie.title,
                color = Color.White,
                fontSize = 28.sp * scale,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.roboto_regular))
            )

            // Year and Rating/Runtime Info
            Text(
                text = "${movie.year} || ${movie.rating} +min",
                color = UserAccount, // Assumed custom theme color
                fontSize = 18.sp * scale,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            // Movie Overview (Expandable)
            Text(
                text = movie.overview,
                color = UserAccount,
                fontSize = 16.sp * scale,
                maxLines = if (showFullDescription) Int.MAX_VALUE else 3,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            // "Read More" Toggle Link
            Text(
                text = if (showFullDescription) "Show Less" else "Read More",
                color = Color(0xFFEC255A), // Accent color for the link
                fontSize = 14.sp * scale,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable { showFullDescription = !showFullDescription }
                    .padding(top = 4.dp)
            )

            // Cast Header
            Text(
                text = "Cast",
                color = Color.White,
                fontSize = 25.sp * scale,
                fontWeight = FontWeight.Bold
            )

            // Cast List (Horizontal Scrollable Row)
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp * scale)) {
                items(cast) { actor ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        AsyncImage(
                            model = actor.imageUrl,
                            contentDescription = actor.name,
                            modifier = Modifier
                                .size(64.dp * scale) // Increased size for better visual
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        // Actor Name (optional, if cast data includes it)
                        // Text(actor.name, color = Color.White, fontSize = 12.sp * scale, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                }
            }

            // Action Buttons Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp * scale),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Favorite Button
                IconButton(
                    onClick = {
                        if (isFavorite) {
                            FavoriteStorage.removeMovie(context, movie.id)
                        } else {
                            FavoriteStorage.saveMovie(context, movie.toMovieUiModel()) // Map to required UI Model
                        }
                        isFavorite = !isFavorite
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Toggle Favorite",
                        tint = if (isFavorite) Color.Red else Color.White,
                        modifier = Modifier.size(30.dp * scale)
                    )
                }

                // Watchlist/Save Button
                IconButton(
                    onClick = {
                        if (isSaved) WatchlistStorage.removeMovie(context, movie.id)
                        // Assuming WatchlistStorage can handle the full MovieDetailsUiModel
                        else WatchlistStorage.saveMovie(context, movie)
                        isSaved = !isSaved
                    }
                ) {
                    Icon(
                        imageVector = if (isSaved) Icons.Default.BookmarkAdded else Icons.Default.BookmarkBorder,
                        contentDescription = "Toggle Watchlist Status",
                        tint = if (isSaved) Color(0xFFFFD700) else Color.White // Gold/Yellow when saved
                    )
                }

                // Watch Now Button (Primary Action)
                Button(
                    onClick = { navController.navigate("movie_content/${movie.id}") },
                    modifier = Modifier
                        .height(50.dp * scale)
                        .weight(1f) // Takes remaining horizontal space
                        .padding(start = 16.dp * scale), // Increased start padding for separation
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