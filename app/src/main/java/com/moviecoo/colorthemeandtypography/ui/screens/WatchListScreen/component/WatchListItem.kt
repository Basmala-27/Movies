package com.moviecoo.colorthemeandtypography.ui.screens.WatchListScreen.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.ui.screens.WatchListScreen.model.MovieDataUiModel
import com.moviecoo.colorthemeandtypography.ui.screens.movieListScreen.model.MovieUiModel
import com.moviecoo.colorthemeandtypography.ui.theme.Primary

@Composable
fun MovieWatchListItem(
    movieUiModel: MovieUiModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF09274C))
            .padding(vertical = 8.dp)
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            shape = MaterialTheme.shapes.large,
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = Primary)

        ) {

            Row(
                modifier = Modifier.padding(12.dp)
                    .background(Color(0xFF09274C)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(

                    modifier = Modifier
                        .size(width = 120.dp, height = 150.dp)
                        .clip(MaterialTheme.shapes.large),
                    //painter = painterResource(id = movieDataUiModel.poster),
                    contentDescription = movieUiModel.title,
                    contentScale = ContentScale.Crop,

                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(movieUiModel.image)
                            .crossfade(1000)
                            .build()

                    ),



                )
                Spacer(modifier = Modifier.width(12.dp))

                Column( verticalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(1f)
                ){
                    Text(
                        text = movieUiModel.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Color.White,
                        maxLines = 1

                    )

                    Spacer(Modifier.height(12.dp))


                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painterResource(R.drawable.baseline_star_border_24),
                            contentDescription = null,
                            Modifier.size(20.dp)
                        )

                        Text(
                            modifier = Modifier.padding(horizontal = 6.dp),
                            text = movieUiModel.rating.toString(),
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color(0xFFFF8700)
                        )


                    }

                    Spacer(modifier = Modifier.height(6.dp))



                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painterResource(R.drawable.ticket),
                            contentDescription = null,
                            Modifier.size(20.dp)
                        )

                        Text(
                            modifier = Modifier.padding(horizontal = 6.dp),
                            text = movieUiModel.genre,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))




                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painterResource(R.drawable.calendar),
                            contentDescription = null,
                            Modifier.size(20.dp)
                        )

                        Text(
                            modifier = Modifier.padding(horizontal = 6.dp),
                            text = movieUiModel.year,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painterResource(R.drawable.clock),
                            contentDescription = null,
                            Modifier.size(20.dp)
                        )

                        Text(
                            modifier = Modifier.padding(horizontal = 6.dp),
                            text = "${movieUiModel.description} minutes",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White ,
                            maxLines = 1
                        )



                    }




                }
            }


        }


    }


}

