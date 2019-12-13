package com.powerincode.domain.di

import com.powerincode.domain.usecases.movies.GetPopularMovies
import com.powerincode.domain.usecases.movies.GetPopularMoviesImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DomainModule {

    @Singleton
    @Binds
    abstract fun bindGetPopularMovieUse(useCase: GetPopularMoviesImpl): GetPopularMovies
}