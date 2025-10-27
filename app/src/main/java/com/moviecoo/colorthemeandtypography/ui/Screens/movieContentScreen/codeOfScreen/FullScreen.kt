package com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.codeOfScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.data.MovieContentData
import com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.data.sampleMovie
import com.moviecoo.colorthemeandtypography.ui.theme.GradientBackground

@Composable
fun MovieContentScreen(movieContentData: MovieContentData) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .background(GradientBackground)
            .verticalScroll(rememberScrollState())

    ) {
        VideoPlayerSection(thumbnailRes = movieContentData.videoThumbnail)
        ContentSection(movieContentData = movieContentData)
    }
}
@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun MovieContentScreenPreview() {
    MovieContentScreen(movieContentData = sampleMovie)
}