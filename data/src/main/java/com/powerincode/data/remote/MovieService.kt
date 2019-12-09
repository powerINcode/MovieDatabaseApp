package com.powerincode.data.remote

import retrofit2.http.GET

interface MovieService {
    @GET("movie/popular")
    fun popular()
}