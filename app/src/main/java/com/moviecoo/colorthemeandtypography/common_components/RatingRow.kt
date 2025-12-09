package com.moviecoo.colorthemeandtypography.common_components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.ceil
import kotlin.math.floor

/**
 * ## RatingRow
 * A composable function that displays a numerical rating alongside a visual 5-star representation.
 *
 * This is designed for movie/content ratings, assuming the input [rating] is on a 10.0 scale,
 * which is then converted to a standard 5-star scale for the icons.
 *
 * @param rating The numerical rating (e.g., 8.5) to be displayed and converted into stars.
 */
@Composable
fun RatingRow(rating: Double) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        // 1. Rating Conversion
        // Scale the input rating (assumed to be out of 10) down to a 5-star scale.
        val ratingOutOf5 = (rating / 2.0).toFloat()

        // 2. Star Calculation Logic
        // Calculate the number of full stars (always rounded down).
        val fullStars = floor(ratingOutOf5).toInt()

        // Check for a half star: true if the rating is greater than the number of full stars
        // but less than the next whole number (i.e., the ceiling is > the floor).
        val halfStar = ceil(ratingOutOf5) > fullStars

        // Calculate the number of empty stars needed to complete the 5-star set.
        val emptyStars = 5 - fullStars - if (halfStar) 1 else 0

        // 3. Render Stars
        // Render Full Stars
        repeat(fullStars) {
            Icon(
                Icons.Filled.Star,
                contentDescription = null, // Content description can be null as the full rating is displayed next to it
                tint = Color(0xFFFFC107), // Gold/Yellow color for ratings
                modifier = Modifier.width(20.dp)
            )
        }
        // Render Half Star
        if (halfStar) {
            Icon(
                Icons.Filled.StarHalf,
                contentDescription = null,
                tint = Color(0xFFFFC107),
                modifier = Modifier.width(20.dp)
            )
        }
        // Render Empty Stars
        repeat(emptyStars) {
            Icon(
                Icons.Filled.StarOutline,
                contentDescription = null,
                tint = Color(0xFFFFC107), // Empty stars use the same tint for consistency
                modifier = Modifier.width(20.dp)
            )
        }

        // 4. Render Numerical Rating
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = rating.toString(), // Displays the original numerical rating (e.g., 8.5)
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}