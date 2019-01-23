package com.nooblabs.tmdb.data

import com.nooblabs.tmdb.domain.Configuration


interface INetworkSource {

    suspend fun getConfiguration(): Configuration
}