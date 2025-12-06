package com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import com.moviecoo.colorthemeandtypography.R

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.viewmodel.MovieListViewModel
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.NetworkModule.provideMovieApi
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.MovieDataModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.componant.AppScreenHeader
import com.moviecoo.colorthemeandtypography.ui.theme.OnPrimary
import com.moviecoo.colorthemeandtypography.ui.theme.OrangeAccent
import com.moviecoo.colorthemeandtypography.ui.theme.Primary
import com.moviecoo.colorthemeandtypography.mapper.toMoviesUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.FontSizeViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MovieListScreen(
    navController: NavController,
    fontSizeViewModel: FontSizeViewModel,
    viewModel: MovieListViewModel = hiltViewModel(),
    onVoiceCommand: (vm: MovieListViewModel) -> Unit,
    onFeaturedClick: () -> Unit = {},
    onRandomClick: () -> Unit = {},
    onGuessClick: () -> Unit = {},
    onSeeAllClick: (String) -> Unit = { _ -> },
    onMovieClick: (MovieUiModel) -> Unit = {} ,
    onAssistantClick: () -> Unit,
) {
    val scale = fontSizeViewModel.fontScale.value

    // Features
    val featuresList = listOf(
        features("Movie to Mood", R.drawable.moodtomovie, onFeaturedClick),
        features("Random Movie", R.drawable.randommovie, onRandomClick),
        features("Guess The Movie", R.drawable.guess, onGuessClick)
    )

    Scaffold (topBar = {
        TopAppBar(

            title = {
                AppScreenHeader(navController = navController)
            },
            actions = {
                IconButton(
                    onClick = onAssistantClick // Call the navigation function
                ) {
                    Icon(
                        Icons.Filled.HelpOutline, // Using a help or chat icon
                        contentDescription = "AI Assistant",
                        tint = OrangeAccent,
                        modifier = Modifier.size(32.dp * scale)
                    )
                }

                IconButton(
                    onClick = { onVoiceCommand(viewModel) }
                ) {
                    Icon(
                        Icons.Filled.Mic,
                        contentDescription = "Voice Command",
                        tint = OrangeAccent,
                        modifier = Modifier.size(32.dp * scale)
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Primary // Use your background color
            )
        )
    }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Primary)
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(25.dp * scale))


            Spacer(modifier = Modifier.height(16.dp * scale))

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 2.dp * scale),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(featuresList) { item ->
                    FeaturedMovieitem(onClick = item.onClick, image = item.image, scale = scale)
                }
            }


            Spacer(modifier = Modifier.height(24.dp * scale))

            // Movie Sections
            listOf(
                "Upcoming" to false,
                "Trending Now" to true,
                "Top Rated" to true,
                "New Releases" to false
            ).forEach { (title, showRating) ->
                Spacer(modifier = Modifier.height(24.dp * scale))
                MovieSection(
                    title = title,
                    showRating = showRating,
                    onMovieClick = onMovieClick,
                    onSeeAllClick = onSeeAllClick,
                    scale = scale
                )
            }
        }
    }
}

data class features(
    val title: String ,
    val image: Int ,
    val onClick: () -> Unit ={}
)





@Composable
fun FeaturedMovieitem(onClick: () -> Unit , image: Int, scale: Float){
    Card(
        modifier = Modifier
            .width(400.dp * scale)
            .height(220.dp * scale)
            .padding(horizontal = 16.dp * scale)
            .clip(RoundedCornerShape(12.dp * scale))
            .clickable { onClick() }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(image),
                contentDescription = "Movie Image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}


@Composable
fun MovieSection(
    title: String,
    showRating: Boolean,
    onSeeAllClick: (String) -> Unit = {},
    onMovieClick: (MovieUiModel) -> Unit = {},
    scale: Float
) {
    val movieListState = remember { mutableStateOf<MovieDataModel?>(null) }

    LaunchedEffect(Unit) {
        val response = when(title) {
            "Trending Now" -> provideMovieApi().fetchMovies()
            "New Releases" -> provideMovieApi().fetchNowPlayingMovies()
            "Upcoming" -> provideMovieApi().fetchUpcomingMovies()
            "Top Rated" -> provideMovieApi().fetchTopRatingMovies()
            else -> null
        }
        movieListState.value = response?.body() as? MovieDataModel
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp * scale, vertical = 8.dp * scale),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, fontSize = 20.sp * scale, color = Color.White)
        TextButton(onClick = { onSeeAllClick(title) }) {
            Text("See All >", color = OrangeAccent, fontSize = 14.sp * scale)
        }
    }

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp * scale),
        horizontalArrangement = Arrangement.spacedBy(12.dp * scale)
    ) {
        movieListState.value?.toMoviesUiModel()?.let { list ->
            items(list) { movie ->
                MovieListItem(movie = movie, showRating = showRating, onClick = { onMovieClick(movie) }, scale = scale)
            }
        }
    }
}

@Composable

fun AnimatedMovieCard(
    corner: Int = 12,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    var pressed by remember { mutableStateOf(false) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.94f else 1f,
        animationSpec = tween(160)
    )

    val tiltX by animateFloatAsState(
        targetValue = offsetY / 20f,
        animationSpec = tween(150)
    )

    val tiltY by animateFloatAsState(
        targetValue = -offsetX / 20f,
        animationSpec = tween(150)
    )

    val shadow by animateFloatAsState(
        targetValue = if (pressed) 20f else 6f,
        animationSpec = tween(200)
    )

    Surface(
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                rotationX = tiltX
                rotationY = tiltY
                shadowElevation = shadow
                clip = true
            }
            // Detect parallax movement (optional: remove on mobile if undesired)
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        val pos = event.changes.firstOrNull()?.position ?: continue
                        offsetX = pos.x - size.width / 2f
                        offsetY = pos.y - size.height / 2f
                    }
                }
            }
            // Detect tap only
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { onClick() },
                    onPress = {
                        pressed = true
                        tryAwaitRelease()
                        pressed = false
                    }
                )
            },
        shape = RoundedCornerShape(corner.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}

@Composable

fun MovieListItem(
    movie: MovieUiModel,
    showRating: Boolean,
    onClick: () -> Unit ={} ,
    modifier: Modifier = Modifier,
    scale: Float
) {
    Column(modifier = modifier.width(160.dp * scale)) {
        AnimatedMovieCard(
            corner = 12,
            modifier = Modifier
                .height(200.dp * scale)
                .width(160.dp * scale),
            onClick = { onClick() }
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = movie.image,
                    contentDescription = movie.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                if (showRating) {
                    RatingBadge(rating = movie.rating, scale = scale)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp * scale))
        Text(
            movie.title,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp * scale,
            maxLines = 1
        )
    }
}


@Composable
fun RatingBadge(rating: Double, scale: Float) {
    Row(
        modifier = Modifier
            .padding(8.dp * scale)
            .clip(RoundedCornerShape(4.dp * scale))
            .background(Color.Black.copy(alpha = 0.6f))
            .padding(horizontal = 6.dp * scale, vertical = 4.dp * scale),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Default.Star,
            contentDescription = "Rating Star",
            tint = OrangeAccent,
            modifier = Modifier.size(14.dp * scale)
        )
        Spacer(modifier = Modifier.width(4.dp * scale))
        Text(
            text = rating.toString(),
            color = Color.White,
            fontSize = 12.sp * scale,
            fontWeight = FontWeight.Bold
        )
    }
}


