package com.moviecoo.colorthemeandtypography.domain.usecase

import com.moviecoo.colorthemeandtypography.domain.model.MoviesDomainModel
import com.moviecoo.colorthemeandtypography.domain.repository.MoviesRepository

class FetchMoviesUseCase(private val moviesRepository: MoviesRepository) {
    operator fun invoke(): List<MoviesDomainModel> {
        return moviesRepository.fetchMovies()



    }}