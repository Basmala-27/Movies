package com.moviecoo.colorthemeandtypography.domain.repository

import com.moviecoo.colorthemeandtypography.domain.model.MoviesDomainModel

interface MoviesRepository {
    fun fetchMovies():List<MoviesDomainModel>
}