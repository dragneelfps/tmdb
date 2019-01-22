package com.nooblabs.tmdb.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageMetadata(
    var base_url: String? = null,
    var secure_base_url: String? = null,
    var backdrop_sizes: List<String>? = null,
    var logo_sizes: List<String>? = null,
    var poster_sizes: List<String>? = null,
    var still_sizes: List<String>? = null
)
