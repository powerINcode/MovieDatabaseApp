package com.powerincode.data.repositories.movies.models.dto

import com.google.gson.annotations.SerializedName
import com.powerincode.middleware.movies.Movie

data class MovieDto(
    @SerializedName("adult") override val adult: Boolean,
    @SerializedName("backdrop_path") override val backdropPath: String,
    @SerializedName("genre_ids") override val genreIds: List<Int>,
    @SerializedName("id") override val id: Long,
    @SerializedName("original_language") override val originalLanguage: String,
    @SerializedName("original_title") override val originalTitle: String,
    @SerializedName("overview") override val overview: String,
    @SerializedName("popularity") override val popularity: Double,
    @SerializedName("poster_path") override val posterPath: String,
    @SerializedName("release_date") override val releaseDate: String,
    @SerializedName("title") override val title: String,
    @SerializedName("video") override val video: Boolean,
    @SerializedName("vote_average") override val voteAverage: Int,
    @SerializedName("vote_count") override val voteCount: Int
): Movie