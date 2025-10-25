package com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model

data class moviedatamodle(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)