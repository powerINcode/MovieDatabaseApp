package com.powerincode.data.repositories.movies

import com.powerincode.core.data.local.PrefModel
import com.powerincode.core.data.local.PrefModelHolder
import com.powerincode.core.data.repositories.datamanager.DataDelegate
import com.powerincode.core.data.repositories.datamanager.DataManager
import com.powerincode.core.domain.Data
import com.powerincode.data.di.qualifiers.StorageType
import com.powerincode.data.local.Storage
import com.powerincode.data.remote.MovieService
import com.powerincode.domain.repositories.MovieRepository
import com.powerincode.middleware.movies.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal class MovieRepositoryImpl @Inject constructor(
    @StorageType.Memory private val memoryStorage: Storage,
    @StorageType.Local private val localStorage: Storage,
    private val movieService: MovieService
    ) : MovieRepository {
    private val moviesDataFlow = DataManager("movie", object : DataDelegate<List<Movie>> {
        override fun getFromMemory(key: String): List<Movie>? = memoryStorage.get(key)

        override fun setToMemory(key: String, value: List<Movie>) {
            memoryStorage.put(key, value)
        }

        override fun getFromStorage(key: String): PrefModelHolder<List<Movie>>? {
            return localStorage.get<PrefModelHolder<List<Movie>>>(key)
        }

        override fun setToStorage(key: String, value: List<Movie>) {
            localStorage.put(key, PrefModelHolder(value, System.currentTimeMillis()))
        }

        override suspend fun getFromNetwork(): List<Movie> {
            delay(1000)
            return movieService.popular().results
        }

        override fun isExpired(key: String, cache: PrefModel): Boolean {
            val lastUpdatedAt = memoryStorage.get<PrefModel>(key)?.lastUpdatedAt ?: return true
            return lastUpdatedAt - System.currentTimeMillis() > TimeUnit.MINUTES.toMillis(1)
        }

    })

    override fun getPopularMovies(force: Boolean): Flow<Data<List<Movie>>> {
        return moviesDataFlow.flow(force)
            .flowOn(Dispatchers.IO)
    }
}