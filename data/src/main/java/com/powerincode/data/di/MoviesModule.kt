package com.powerincode.data.di

import android.content.Context
import com.google.gson.Gson
import com.powerincode.core.di.qualifiers.ApplicationContext
import com.powerincode.data.di.qualifiers.StorageType.Local
import com.powerincode.data.di.qualifiers.StorageType.Memory
import com.powerincode.data.local.LocalStorage
import com.powerincode.data.local.MemoryCache
import com.powerincode.data.local.Storage
import com.powerincode.data.remote.MovieService
import com.powerincode.data.repositories.movies.MovieRepositoryImpl
import com.powerincode.domain.repositories.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(
    includes = [
        DataModule::class
    ]
)
abstract class MoviesModule {

    @Singleton
    @Binds
    internal abstract fun bindsMovieRepository(repo: MovieRepositoryImpl): MovieRepository


    @Module
    companion object {
        private val name: String = "MoviesModule"

        @JvmStatic
        @Singleton
        @Provides
        @Memory
        fun provideMemoryCache(): Storage {
            return MemoryCache(name)
        }

        @JvmStatic
        @Singleton
        @Provides
        @Local
        fun provideLocalStorage(@ApplicationContext context: Context, gson: Gson): Storage {
            return LocalStorage(name, context, gson)
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideMovieService(retrofit: Retrofit): MovieService {
            return retrofit.create(MovieService::class.java)
        }
    }

}