package com.nooblabs.tmdb.domain

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Configuration(
    @Embedded var images: ImageMetadata? = null,
    var change_keys: List<String>? = null,
    @PrimaryKey var timestamp: Long? = null // for internal purposes
)