package com.nooblabs.tmdb.network

import com.nooblabs.tmdb.domain.Configuration
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface NetworkService {

    @GET("configuration")
    fun getConfiguration(): Deferred<Configuration>

}