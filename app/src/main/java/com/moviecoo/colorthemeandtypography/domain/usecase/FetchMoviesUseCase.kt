package com.moviecoo.colorthemeandtypography.domain.usecase

import com.moviecoo.colorthemeandtypography.domain.model.MoviesDomainModel
import com.moviecoo.colorthemeandtypography.domain.repository.MoviesRepository
import javax.inject.Inject

class FetchMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(): List<MoviesDomainModel> {
        return moviesRepository.fetchMovies()



    }}