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

@Composable
fun RatingRow(rating: Double) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        val ratingOutOf5 = (rating / 2.0).toFloat()
        val fullStars = floor(ratingOutOf5).toInt()
        val halfStar = ceil(ratingOutOf5) > fullStars
        val emptyStars = 5 - fullStars - if (halfStar) 1 else 0

        repeat(fullStars) {
            Icon(Icons.Filled.Star, contentDescription = null, tint = Color(0xFFFFC107), modifier = Modifier.width(20.dp))
        }
        if (halfStar) {
            Icon(Icons.Filled.StarHalf, contentDescription = null, tint = Color(0xFFFFC107), modifier = Modifier.width(20.dp))
        }
        repeat(emptyStars) {
            Icon(Icons.Filled.StarOutline, contentDescription = null, tint = Color(0xFFFFC107), modifier = Modifier.width(20.dp))
        }

        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = rating.toString(),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}