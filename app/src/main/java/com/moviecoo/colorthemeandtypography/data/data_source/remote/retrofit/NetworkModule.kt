package com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit

import com.moviecoo.colorthemeandtypography.data.Constants.Companion.BASE_URL
import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.api.MovieApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideMovieApi(): MovieApi{
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieApi::class.java)


}