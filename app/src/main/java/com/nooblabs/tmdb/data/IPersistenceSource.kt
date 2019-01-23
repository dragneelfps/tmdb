package com.nooblabs.tmdb.data

import com.nooblabs.tmdb.domain.Configuration
import com.nooblabs.tmdb.domain.TrendingMovie

interface IPersistenceSource {
    suspend fun getPersistedConfiguration(): Configuration?
    fun saveNewConfiguration(configuration: Configuration)


    suspend fun getPersistedTrendingMovies(): List<TrendingMovie>
    fun saveNewTrendingMovie(movie: TrendingMovie)
    fun saveAllNewTrendingMovie(movies: List<TrendingMovie>)
}