package com.moviecoo.colorthemeandtypography.domain.di



import com.moviecoo.colorthemeandtypography.domain.repository.MoviesRepository
import com.moviecoo.colorthemeandtypography.domain.usecase.FetchMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideFetchMoviesUseCase(
        repository: MoviesRepository
    ): FetchMoviesUseCase {
        return FetchMoviesUseCase(repository)
    }
}






