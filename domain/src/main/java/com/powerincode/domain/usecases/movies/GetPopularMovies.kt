package com.powerincode.domain.usecases.movies

import com.powerincode.core.domain.repositories.Data
import com.powerincode.core.domain.usecase.UseCase
import com.powerincode.domain.repositories.MovieRepository
import com.powerincode.middleware.movies.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPopularMoviesImpl @Inject constructor(private val movieRepository: MovieRepository): GetPopularMovies {
    override fun invoke(params: Boolean?): Flow<Data<List<Movie>>> = movieRepository.getPopularMovies(params)
}

interface GetPopularMovies: UseCase<Boolean?, Flow<Data<List<Movie>>>>