package com.nooblabs.tmdb.framework

import com.nooblabs.tmdb.data.IPersistenceSource
import com.nooblabs.tmdb.database.dao.ConfigurationDao
import com.nooblabs.tmdb.database.dao.TrendingMoviesDao
import com.nooblabs.tmdb.domain.Configuration
import com.nooblabs.tmdb.domain.TrendingMovie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomPersistenceSource @Inject constructor(
    private val configurationDao: ConfigurationDao,
    private val trendingMoviesDao: TrendingMoviesDao
) :
    IPersistenceSource {
    override suspend fun getPersistedConfiguration(): Configuration? {
        return configurationDao.getAllConfigurations().maxBy { it.timestamp ?: 0 }
    }

    override fun saveNewConfiguration(configuration: Configuration) {
        configurationDao.insertConfiguration(configuration)
    }

    override suspend fun getPersistedTrendingMovies(): List<TrendingMovie> {
        return trendingMoviesDao.getAllTrendingMovies()
    }

    override fun saveNewTrendingMovie(movie: TrendingMovie) {
        trendingMoviesDao.insertTrendingMovie(movie)
    }

    override fun saveAllNewTrendingMovie(movies: List<TrendingMovie>) {
        trendingMoviesDao.insertAllTrendingMovies(movies)
    }

}