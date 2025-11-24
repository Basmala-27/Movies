package com.moviecoo.colorthemeandtypography.ui.screens.detailsScreen.shimmerEffect

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailsLoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ShimmerEffect(modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)) // مكان صورة الفيلم

        ShimmerEffect(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)) // عنوان الفيلم

        ShimmerEffect(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)) // سنة و تقييم

        repeat(3) {
            ShimmerEffect(modifier = Modifier
                .fillMaxWidth()
                .height(18.dp)) // نص الفلم
        }
    }
}
