package com.powerincode.data.remote

import com.powerincode.data.repositories.movies.models.dto.MoviePageDto
import retrofit2.http.GET

interface MovieService {
    @GET("movie/popular")
    suspend fun popular(): MoviePageDto
}