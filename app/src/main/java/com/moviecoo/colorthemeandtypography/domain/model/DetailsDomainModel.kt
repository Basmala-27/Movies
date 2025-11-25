package com.moviecoo.colorthemeandtypography.domain.model

data class DetailsDomainModel(
    val id : Int,
    val title: String,
    val year: String,
    val genre: String,
    val duration: String,
    val rating: Double,
    val overview: String,
    val image: String,
    val castImages: List<Int> = emptyList()
)
