package com.powerincode.moviedatabaseapp.main

import androidx.lifecycle.LiveData
import com.powerincode.middleware.movies.Movie

interface MainScreenContract {
    interface ViewModel {
        val movies: LiveData<List<Movie>>
    }
}