package com.moviecoo.colorthemeandtypography.domain.repository

interface MoviesRepository {
    fun fetchMovies():List<MoviesDomainModel>
}