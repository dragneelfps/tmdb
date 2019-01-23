package com.nooblabs.tmdb.network

import com.nooblabs.tmdb.domain.Configuration
import com.nooblabs.tmdb.domain.TrendingMovie
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {

    @GET("configuration")
    fun getConfiguration(): Deferred<Configuration>

    @GET("trending/{media_type}/{time_window}")
    fun getTrendingMovies(
        @Path("media_type") mediaType: String = "all",
        @Path("time_window") timeWindow: String = "week"
    ): Deferred<GenericResponse<TrendingMovie>>

}