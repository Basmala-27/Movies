package com.moviecoo.colorthemeandtypography.ui.screens.guessTheMovieScreen

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.FontSizeViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.guessTheMovieScreen.viewModel.GuessMovieViewModel

// --- Revised Color Palette matching the CineMystery banner vibe ---
val BackgroundDeepBlue = Color(0xFF0F172A) // Slate/Navy Background
val SurfaceDarker = Color(0xFF1E293B)     // Darker surfaces for cards
val AccentVibrantBlue = Color(0xFF4C8DFE) // Bright blue for selected state/action
val AccentVibrantPurple = Color(0xFF8B5CF6) // Purple for primary actions
val TextLight = Color(0xFFFFFFFF)

@Composable
fun GuessMovieScreen(viewModel: GuessMovieViewModel, fontSizeViewModel: FontSizeViewModel) {
    val scale = fontSizeViewModel.fontScale.value
    val movie = viewModel.movies[viewModel.currentIndex]
    val selectedOption = viewModel.selectedAnswer
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    Scaffold(
        modifier = Modifier.background(BackgroundDeepBlue),
        topBar = {
            Column {
                IconButton(
                    modifier = Modifier.padding(top = 10.dp * scale),
                    onClick = { backDispatcher?.onBackPressed() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp * scale)
                    )
                }

                ScoreBanner(score = viewModel.score, scale = scale)
            }
        },
        bottomBar = {
            NextMovieButton(onClick = viewModel::nextMovie, scale = scale)
        },
        containerColor = BackgroundDeepBlue,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp * scale, vertical = 10.dp * scale),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                MoviePoster(
                    posterUrl = movie.poster,
                    contentDescription = movie.title,
                    scale = scale
                )

                Spacer(Modifier.height(28.dp * scale))

                OptionsGrid(
                    options = movie.options,
                    selectedOption = selectedOption,
                    onOptionSelected = viewModel::selectAnswer,
                    scale = scale
                )

                Spacer(Modifier.height(16.dp * scale))
            }
        }
    )
}


// --- Component Breakdown for Bolder Cinematic Vibe ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreBanner(score: Int, scale: Float) { // خد scale كـ parameter
    TopAppBar(
        modifier = Modifier.padding(top = 10.dp * scale), // تعديل padding حسب scale

        title = {
            Text(
                text = "🎬 CineMystery",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp * scale, // تكبير/تصغير proportional
                color = TextLight
            )
        },
        actions = {
            Card(
                shape = RoundedCornerShape(10.dp * scale),
                colors = CardDefaults.cardColors(
                    containerColor = SurfaceDarker
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp * scale),
                modifier = Modifier.padding(end = 16.dp * scale)
            ) {
                Text(
                    text = "SCORE $score",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Black,
                    fontSize = 14.sp * scale, // تكبير/تصغير proportional
                    color = AccentVibrantBlue,
                    modifier = Modifier.padding(horizontal = 12.dp * scale, vertical = 8.dp * scale)
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
fun MoviePoster(posterUrl: String, contentDescription: String, scale: Float) {

    Card(
        shape = RoundedCornerShape(16.dp * scale), // More rounded corners
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp * scale), // Deeper shadow
        colors = CardDefaults.cardColors(containerColor = SurfaceDarker),
        modifier = Modifier
            .fillMaxWidth(0.95f) // width نسبي ما يتغيرش
            .height(380.dp * scale) // ارتفاع يتغير حسب scale
    ) {
        Image(
            painter = rememberAsyncImagePainter(posterUrl),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp * scale)) // نفس الزوايا تتغير proportionally
        )
    }
}


@Composable
fun OptionsGrid(
    options: List<String>,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit,
    scale: Float
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp * scale),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        options.chunked(2).forEach { rowOptions ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp * scale),
                horizontalArrangement = Arrangement.SpaceEvenly // spacing نسبي
            ) {
                rowOptions.forEach { option ->
                    OptionPillButton(
                        option = option,
                        isSelected = option == selectedOption,
                        onClick = { onOptionSelected(option) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 6.dp * scale),
                        scale = scale
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
    modifier: Modifier = Modifier,
    scale: Float = 1f  // default 1f لو مش حابة تمرري
) {
    val containerColor = if (isSelected) AccentVibrantBlue else SurfaceDarker.copy(alpha = 0.8f)
    val contentColor = if (isSelected) TextLight else TextLight.copy(alpha = 0.9f)

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(percent = 50),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor, contentColor = contentColor),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 6.dp * scale,
            pressedElevation = 2.dp * scale
        ),
        modifier = modifier
            .heightIn(min = 55.dp * scale)
            .padding(vertical = 4.dp * scale)
    ) {
        Text(
            text = option,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp * scale,
            maxLines = 2
        )
    }
}


@Composable
fun NextMovieButton(onClick: () -> Unit, scale: Float = 1f) {
    BottomAppBar(
        containerColor = BackgroundDeepBlue,
        modifier = Modifier.height(80.dp * scale),
        contentPadding = PaddingValues(horizontal = 20.dp * scale, vertical = 10.dp * scale)
    ) {
        ExtendedFloatingActionButton(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            containerColor = AccentVibrantPurple,
            contentColor = TextLight,
            shape = RoundedCornerShape(16.dp * scale),
            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 8.dp * scale),
            icon = { Icon(Icons.Filled.NavigateNext, contentDescription = "Next") },
            text = { Text("NEXT CHALLENGE", fontWeight = FontWeight.ExtraBold, fontSize = 17.sp * scale) }
        )
    }
}
