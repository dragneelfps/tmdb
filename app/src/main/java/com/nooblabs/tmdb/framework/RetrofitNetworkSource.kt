package com.nooblabs.tmdb.framework

import com.nooblabs.tmdb.data.ConfigurationNetworkSource
import com.nooblabs.tmdb.domain.Configuration
import com.nooblabs.tmdb.network.NetworkService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitNetworkSource @Inject constructor(private val networkService: NetworkService): ConfigurationNetworkSource {

    override suspend fun getConfiguration(): Configuration {
        return networkService.getConfiguration().await()
    }
}