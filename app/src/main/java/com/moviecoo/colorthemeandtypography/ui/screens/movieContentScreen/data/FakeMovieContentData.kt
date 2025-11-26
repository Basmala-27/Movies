package com.moviecoo.colorthemeandtypography.ui.screens.movieContentScreen.data
import com.moviecoo.colorthemeandtypography.R

val sampleMovie = MovieContentData(
    title = "Venom 2",
    rating = 6.1,
    year = 2021,
    genre = "Action Fantasy",
    duration = "1h 37min",
    description = "Eddie Brock attempts to reignite his career by interviewing serial killer Cletus Kasady," +
            " who becomes the host of the symbiote Carnage and escapes prison after a failed execution.",
    videoThumbnail = R.drawable.film_photo,
    cast = listOf(
        R.drawable.actor1,
        R.drawable.actor2,
        R.drawable.actor3,
        R.drawable.actor4
    ),
    upNext = listOf(
        UpNextMovie(posterRes = R.drawable.movie_avengers, title1 =  "Avengers"),
        UpNextMovie(posterRes = R.drawable.movie_bosslevel, title1 = "Boss Level"),
        UpNextMovie(posterRes = R.drawable.movie_wrathofman, title1 =  "Wrath of Man"),
        UpNextMovie(posterRes = R.drawable.movie_notimetodie, title1 =  "No Time To...")
    ),
    id = 1
)