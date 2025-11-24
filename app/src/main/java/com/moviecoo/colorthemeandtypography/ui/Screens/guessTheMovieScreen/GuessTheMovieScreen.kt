package com.moviecoo.colorthemeandtypography.ui.screens.guessTheMovieScreen

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.moviecoo.colorthemeandtypography.ui.screens.guessTheMovieScreen.viewModel.GuessMovieViewModel

// --- Revised Color Palette matching the CineMystery banner vibe ---
val BackgroundDeepBlue = Color(0xFF0F172A) // Slate/Navy Background
val SurfaceDarker = Color(0xFF1E293B)     // Darker surfaces for cards
val AccentVibrantBlue = Color(0xFF4C8DFE) // Bright blue for selected state/action
val AccentVibrantPurple = Color(0xFF8B5CF6) // Purple for primary actions
val TextLight = Color(0xFFFFFFFF)

@Composable
fun GuessMovieScreen(viewModel: GuessMovieViewModel) {
    val movie = viewModel.movies[viewModel.currentIndex]
    val selectedOption = viewModel.selectedAnswer

    Scaffold(
        modifier = Modifier.background(BackgroundDeepBlue),
        topBar = {
            ScoreBanner(score = viewModel.score)
        },
        bottomBar = {
            NextMovieButton(onClick = viewModel::nextMovie)
        },
        containerColor = BackgroundDeepBlue,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Movie Poster Section (Using more vertical space)
                MoviePoster(posterUrl = movie.poster, contentDescription = movie.title)

                Spacer(Modifier.height(28.dp))

                OptionsGrid(
                    options = movie.options,
                    selectedOption = selectedOption,
                    onOptionSelected = viewModel::selectAnswer
                )

                // Add flexible spacer here to push the poster up slightly
                Spacer(Modifier.height(16.dp))
            }
        }
    )
}

// --- Component Breakdown for Bolder Cinematic Vibe ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreBanner(score: Int) {
    TopAppBar(
        modifier = Modifier.padding(top = 10.dp),
        title = {

            Text(
                text = "🎬 CineMystery",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
                color = TextLight ,


                )
        },
        actions = {
            // Score Card: Clean, high-contrast display
            Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = SurfaceDarker
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Text(
                    text = "SCORE $score",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Black,
                    color = AccentVibrantBlue, // Score number in vibrant accent color
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BackgroundDeepBlue,
            titleContentColor = TextLight
        )
    )
}

@Composable
fun MoviePoster(posterUrl: String, contentDescription: String) {
    // Large, rounded, and elevated card
    Card(
        shape = RoundedCornerShape(16.dp), // More rounded corners
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp), // Deeper shadow
        colors = CardDefaults.cardColors(containerColor = SurfaceDarker),
        modifier = Modifier
            .fillMaxWidth(0.95f) // Wider card
            .height(380.dp) // Taller image
    ) {
        Image(
            painter = rememberAsyncImagePainter(posterUrl),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
        )
    }
}

@Composable
fun OptionsGrid(
    options: List<String>,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        options.chunked(2).forEach { rowOptions ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly // Use SpaceEvenly for consistent spacing
            ) {
                rowOptions.forEach { option ->
                    OptionPillButton(
                        option = option,
                        isSelected = option == selectedOption,
                        onClick = { onOptionSelected(option) },
                        modifier = Modifier.weight(1f).padding(horizontal = 6.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun OptionPillButton(
    option: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val containerColor = when {
        isSelected -> AccentVibrantBlue // Highlight with bright blue
        else -> SurfaceDarker.copy(alpha = 0.8f) // Slightly transparent dark surface
    }
    val contentColor = if (isSelected) TextLight else TextLight.copy(alpha = 0.9f)

    // Use the pill shape for a more unique, creative button style
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(percent = 50), // Pill shape
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 6.dp,
            pressedElevation = 2.dp
        ),
        modifier = modifier
            .heightIn(min = 55.dp)
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = option,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            maxLines = 2
        )
    }
}

@Composable
fun NextMovieButton(onClick: () -> Unit) {
    BottomAppBar(
        containerColor = BackgroundDeepBlue,
        modifier = Modifier.height(80.dp),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
    ) {
        ExtendedFloatingActionButton(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            // Use the purple accent color for the main action
            containerColor = AccentVibrantPurple,
            contentColor = TextLight,
            shape = RoundedCornerShape(16.dp),
            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 8.dp),
            icon = { Icon(Icons.Filled.NavigateNext, contentDescription = "Next") },
            text = { Text("NEXT CHALLENGE", fontWeight = FontWeight.ExtraBold, fontSize = 17.sp) }
        )
    }
}