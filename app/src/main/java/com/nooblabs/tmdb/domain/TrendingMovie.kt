package com.nooblabs.tmdb.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class TrendingMovie(
    var poster_path: String? = null,
    var adult: Boolean? = null,
    var overview: String? = null,
    var release_date: String? = null,
    var genre_ids: List<String>? = null,
    @PrimaryKey var id: Long? = null,
    var original_title: String? = null,
    var original_language: String? = null,
    var title: String? = null,
    var backdrop_path: String? = null,
    var popularity: Float? = null,
    var vote_count: Long? = null,
    var video: Boolean? = null,
    var vote_average: Float? = null
)