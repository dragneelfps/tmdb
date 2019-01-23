package com.nooblabs.tmdb.data

import com.nooblabs.tmdb.domain.Configuration

interface IPersistenceSource {
    suspend fun getPersistedConfiguration(): Configuration?
    fun saveNewConfiguration(configuration: Configuration)
}