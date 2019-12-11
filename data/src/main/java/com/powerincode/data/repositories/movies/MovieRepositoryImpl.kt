package com.powerincode.data.repositories.movies

import com.google.gson.reflect.TypeToken
import com.powerincode.core.data.local.PrefModel
import com.powerincode.core.data.local.PrefModelHolder
import com.powerincode.core.data.repositories.datamanager.DataDelegate
import com.powerincode.core.data.repositories.datamanager.DataManager
import com.powerincode.core.domain.repositories.Data
import com.powerincode.data.di.qualifiers.StorageType
import com.powerincode.data.local.Storage
import com.powerincode.data.remote.MovieService
import com.powerincode.data.repositories.movies.models.dto.MovieDto
import com.powerincode.domain.repositories.MovieRepository
import com.powerincode.middleware.movies.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal class MovieRepositoryImpl @Inject constructor(
    @StorageType.Memory private val memoryStorage: Storage,
    @StorageType.Local private val localStorage: Storage,
    private val movieService: MovieService
    ) : MovieRepository {
    private val moviesDataFlow = DataManager("movie", object : DataDelegate<List<Movie>> {
        override fun getFromMemory(key: String): PrefModelHolder<List<Movie>>? = memoryStorage.get(key, getType())

        override fun setToMemory(key: String, value: PrefModelHolder<List<Movie>>) {
            memoryStorage.put(key, value)
        }

        override fun getFromStorage(key: String): PrefModelHolder<List<Movie>>? {
            return localStorage.get<PrefModelHolder<List<Movie>>>(key, getType())
        }

        override fun setToStorage(key: String, value: PrefModelHolder<List<Movie>>) {
            localStorage.put(key, value)
        }

        override suspend fun getFromNetwork(): List<Movie> {
            delay(1000)
            return movieService.popular().results
        }

        override fun isExpired(key: String, cache: PrefModel): Boolean {
            val lastUpdatedAt = cache.lastUpdatedAt
            return System.currentTimeMillis() - lastUpdatedAt > TimeUnit.MINUTES.toMillis(1)
        }

        override fun getType(): Type = object: TypeToken<PrefModelHolder<List<MovieDto>>>(){}.type
    })

    override fun getPopularMovies(force: Boolean): Flow<Data<List<Movie>>> {
        return moviesDataFlow.flow(force)
            .flowOn(Dispatchers.IO)
    }
}