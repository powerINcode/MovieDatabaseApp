package com.powerincode.domain.repositories

import com.powerincode.core.domain.repositories.Data
import com.powerincode.middleware.movies.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(force: Boolean?): Flow<Data<List<Movie>>>
}