package com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model

data class MovieDataModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)