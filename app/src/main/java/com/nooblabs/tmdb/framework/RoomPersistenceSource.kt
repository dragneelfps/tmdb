package com.nooblabs.tmdb.framework

import com.nooblabs.tmdb.data.IPersistenceSource
import com.nooblabs.tmdb.database.dao.ConfigurationDao
import com.nooblabs.tmdb.domain.Configuration
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomPersistenceSource @Inject constructor(private val configurationDao: ConfigurationDao) :
    IPersistenceSource {

    override suspend fun getPersistedConfiguration(): Configuration? {
        return configurationDao.getAllConfigurations().maxBy { it.timestamp ?: 0 }
    }

    override fun saveNewConfiguration(configuration: Configuration) {
        configurationDao.insertConfiguration(configuration)
    }
}