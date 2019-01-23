package com.nooblabs.tmdb.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenericResponse<T>(
    var page: Long? = null,
    var results: List<T>? = null,
    var total_pages: Long? = null,
    var total_results: Long? = null
)