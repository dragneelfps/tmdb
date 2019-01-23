package com.nooblabs.tmdb.data

import com.nooblabs.tmdb.domain.TrendingMovie
import javax.inject.Inject

class TrendingMoviesRepository @Inject constructor(
    private val trendingMoviesPersistenceSource: IPersistenceSource,
    private val trendingMoviesNetworkSource: INetworkSource
) {

    suspend fun getSavedTrendingMovies(): List<TrendingMovie> =
        trendingMoviesPersistenceSource.getPersistedTrendingMovies()

    suspend fun requestNewMovies(): List<TrendingMovie> {
        val movies = trendingMoviesNetworkSource.getTrendingMovies()
        trendingMoviesPersistenceSource.saveAllNewTrendingMovie(movies)
        return movies
    }

}