package com.powerincode.data.repositories.movies.models.dto

data class MoviePageDto(
    val page: Int,
    val results: List<MovieDto>
)