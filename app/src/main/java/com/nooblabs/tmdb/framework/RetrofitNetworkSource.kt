package com.nooblabs.tmdb.framework

import com.nooblabs.tmdb.data.INetworkSource
import com.nooblabs.tmdb.domain.Configuration
import com.nooblabs.tmdb.domain.TrendingMovie
import com.nooblabs.tmdb.network.NetworkService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitNetworkSource @Inject constructor(private val networkService: NetworkService): INetworkSource {
    override suspend fun getConfiguration(): Configuration {
        return networkService.getConfiguration().await()
    }

    override suspend fun getTrendingMovies(): List<TrendingMovie> {
        return networkService.getTrendingMovies().await().results ?: emptyList()
    }
}