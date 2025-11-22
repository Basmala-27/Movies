package com.moviecoo.colorthemeandtypography.domain.di

import com.moviecoo.colorthemeandtypography.domain.repository.MoviesRepository
import com.moviecoo.colorthemeandtypography.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{

    @Binds
    @Singleton
    abstract fun bindMoviesRepository(
        impl: MovieRepositoryImpl
    ): MoviesRepository
}
