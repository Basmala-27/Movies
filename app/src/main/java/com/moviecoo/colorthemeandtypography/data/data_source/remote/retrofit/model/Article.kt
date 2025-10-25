package com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model

import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.Source

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)