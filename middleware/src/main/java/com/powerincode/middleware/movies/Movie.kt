package com.powerincode.middleware.movies

interface Movie {
    val adult: Boolean
    val backdropPath: String
    val genreIds: List<Int>
    val id: Long
    val originalLanguage: String
    val originalTitle: String
    val overview: String
    val popularity: Double
    val posterPath: String
    val releaseDate: String
    val title: String
    val video: Boolean
    val voteAverage: Float
    val voteCount: Int
}