package com.nooblabs.tmdb.data

import com.nooblabs.tmdb.domain.Configuration
import com.nooblabs.tmdb.domain.TrendingMovie


interface INetworkSource {

    suspend fun getConfiguration(): Configuration

    suspend fun getTrendingMovies(): List<TrendingMovie>
}