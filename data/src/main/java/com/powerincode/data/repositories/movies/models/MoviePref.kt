package com.powerincode.data.repositories.movies.models

import com.powerincode.core.data.local.PrefModel
import com.powerincode.data.repositories.movies.models.dto.MovieDto
import com.powerincode.middleware.movies.Movie

class MoviePref(
    override val adult: Boolean,
    override val backdropPath: String,
    override val genreIds: List<Int>,
    override val id: Long,
    override val originalLanguage: String,
    override val originalTitle: String,
    override val overview: String,
    override val popularity: Double,
    override val posterPath: String,
    override val releaseDate: String,
    override val title: String,
    override val video: Boolean,
    override val voteAverage: Int,
    override val voteCount: Int,
    override val lastUpdatedAt: Long
) : Movie, PrefModel