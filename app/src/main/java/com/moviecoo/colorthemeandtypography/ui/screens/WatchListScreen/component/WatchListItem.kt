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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moviecoo.colorthemeandtypography.R
import com.moviecoo.colorthemeandtypography.ui.screens.WatchListScreen.model.MovieDataUiModel
import com.moviecoo.colorthemeandtypography.ui.theme.Primary

@Composable
fun MovieWatchListItem(
    modifier: Modifier = Modifier,
    movieDataUiModel: MovieDataUiModel
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
                    painter = painterResource(id = movieDataUiModel.poster),
                    contentDescription = movieDataUiModel.name,
                    contentScale = ContentScale.Crop

//                    painter = rememberAsyncImagePainter(
//                        ImageRequest.Builder(LocalContext.current)
//                            .data(movieDataUiModel.poster)
//                            .crossfade(1000)
//                            .build()
//
//                    ),



                )
                Spacer(modifier = Modifier.width(12.dp))

                Column( verticalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(1f)
                ){
                    Text(
                        text = movieDataUiModel.name,
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
                            text = movieDataUiModel.rate.toString(),
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
                            text = movieDataUiModel.tickets.toString(),
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
                            text = movieDataUiModel.date.toString(),
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
                            text = "${movieDataUiModel.rate} minutes",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White
                        )



                    }




                }
            }


        }


    }


}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun PreviewMovieWatchListItem() {
    MovieWatchListItem(
        movieDataUiModel = MovieDataUiModel(
            name = "spiderman",
            rate = 1.2,
            tickets = 1,
            date = 5,
            durationMinutes = 1,
            poster = R.drawable.homem_aranha
        )
    )

}