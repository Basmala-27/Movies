package com.moviecoo.colorthemeandtypography.ui.screens.WatchListScreen.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.ui.screens.signInScreen.fontSizeViewModel.FontSizeViewModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel
import com.moviecoo.colorthemeandtypography.ui.theme.Primary
@Composable
fun MovieWatchListItem(
    movieUiModel: MovieUiModel,
    fontSizeViewModel: FontSizeViewModel,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit // ← أضيفي هنا
) {
    val scale = fontSizeViewModel.fontScale.value

    Box(
        modifier = Modifier
            .fillMaxWidth() // بدل fillMaxSize()
            .background(Color(0xFF09274C))
            .padding(vertical = 8.dp * scale)
            .clickable { onClick() }   // ← هنا

    ){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp * scale, vertical = 8.dp * scale)
                .clickable { onClick() },   // ← هنا
            shape = MaterialTheme.shapes.large,
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = Primary)

        ) {
            Row(
                modifier = Modifier.padding(12.dp * scale)
                    .background(Color(0xFF09274C)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(width = 120.dp * scale, height = 150.dp * scale)
                        .clip(MaterialTheme.shapes.large),
                    contentDescription = movieUiModel.title,
                    contentScale = ContentScale.Crop,
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(movieUiModel.image)
                            .crossfade(1000)
                            .build()
                    ),
                )
                Spacer(modifier = Modifier.width(12.dp * scale))

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(1f)
                ){
                    Text(
                        text = movieUiModel.title,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 18.sp * scale, // مثال
                        color = Color.White,
                        maxLines = 1
                    )

                    Spacer(Modifier.height(12.dp * scale))

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painterResource(R.drawable.baseline_star_border_24),
                            contentDescription = null,
                            Modifier.size(20.dp * scale)
                        )

                        Text(
                            modifier = Modifier.padding(horizontal = 6.dp * scale),
                            text = movieUiModel.rating.toString(),
                            fontSize = 14.sp * scale,
                            color = Color(0xFFFF8700)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp * scale))

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painterResource(R.drawable.ticket),
                            contentDescription = null,
                            Modifier.size(20.dp * scale)
                        )

                        Text(
                            modifier = Modifier.padding(horizontal = 6.dp * scale),
                            text = movieUiModel.genre,
                            fontSize = 14.sp * scale,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp * scale))

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painterResource(R.drawable.calendar),
                            contentDescription = null,
                            Modifier.size(20.dp * scale)
                        )

                        Text(
                            modifier = Modifier.padding(horizontal = 6.dp * scale),
                            text = movieUiModel.year,
                            fontSize = 14.sp * scale,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp * scale))

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painterResource(R.drawable.clock),
                            contentDescription = null,
                            Modifier.size(20.dp * scale)
                        )

                        Text(
                            modifier = Modifier.padding(horizontal = 6.dp * scale),
                            text = "${movieUiModel.description} minutes",
                            fontSize = 14.sp * scale,
                            color = Color.White,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}
