package com.moviecoo.colorthemeandtypography.ui.screens.moodToMovieScreen.moodToMovieViweModel

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.moviecoo.colorthemeandtypography.common_components.TopAppBar
import com.moviecoo.colorthemeandtypography.R

val DarkBackground = Color(0xFF1E1E2C)
val CardBackground = Color(0xFF2C2C3E)
val LightGrayText = Color(0xFFB0B0C4)


data class MoodItem(
    val name: String,
    val genreId: String,
    val icon: ImageVector,
    val color: Color
)


val moodsData = listOf(
    MoodItem("Happy", "35", Icons.Filled.SentimentSatisfied, Color(0xFFFDD835)), // Yellow
    MoodItem("Romantic", "10749", Icons.Filled.Favorite, Color(0xFFE91E63)), // Pink/Heart
    MoodItem("Sad", "18", Icons.Filled.SentimentDissatisfied, Color(0xFF42A5F5)), // Blue
    MoodItem("Bored", "10770", Icons.Filled.Mail, Color(0xFFFF9800)), // Orange/Envelope
    MoodItem("Inspiring", "99", Icons.Filled.Lightbulb, Color(0xFFBBDEFB)), // Light Blue/Bulb
    MoodItem("Thrilling", "53", Icons.Filled.LocalFireDepartment, Color(0xFFF44336)), // Red/Fire
    MoodItem("Action", "28", Icons.Filled.FlashOn, Color(0xFF7CB342)), // Green/Flash
    MoodItem("Horror", "27", Icons.Filled.Dangerous, Color(0xFF6A1B9A)) // Purple/Skull
)


@Composable
fun MoodCard(
    item: MoodItem,
    onMoodSelected: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .height(140.dp)
            .fillMaxWidth()
            .clickable { onMoodSelected(item.genreId) }, // Make the whole card clickable
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {

            Icon(
                imageVector = item.icon,
                contentDescription = item.name,
                tint = item.color,
                modifier = Modifier.size(40.dp)
            )


            Text(
                text = item.name,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoodSelectionScreen(onMoodSelected: (String) -> Unit) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = DarkBackground,

        ) { paddingValues ->
        TopBarIcons()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                Row {
                    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

                    IconButton(onClick = {

                        backDispatcher?.onBackPressed()
                    }) {

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )


                    }
                }

                Text(

                    text = "What's your mood like?",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp , start = 10.dp)
                )
                Text(
                    text = "Choose a mood and let us recommend for you.",
                    color = LightGrayText,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 24.dp , start = 10.dp)
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(0.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(moodsData) { mood ->
                        MoodCard(item = mood, onMoodSelected = onMoodSelected)
                    }
                }
            }

    }
}

@Composable
fun TopBarIcons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = "Profile",
            tint = LightGrayText,
            modifier = Modifier.size(24.dp).padding(end = 16.dp)
        )

        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "Premium",
            tint = Color(0xFFFDD835), // Yellow tint
            modifier = Modifier.size(24.dp).padding(end = 16.dp)
        )

        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Favorites",
            tint = Color(0xFFE91E63), // Pink tint
            modifier = Modifier.size(24.dp)
        )
    }
}