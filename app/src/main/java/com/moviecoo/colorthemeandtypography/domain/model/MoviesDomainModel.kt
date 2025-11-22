package com.moviecoo.colorthemeandtypography.domain.model

import android.media.Image

data class  MoviesDomainModel (
    val title: String,
    val year: String,
    val durationMin: Int,
    val genre: String,
    val rating: Double,
    val image: String,
    val description: String? = null

)
