package com.nooblabs.tmdb.data

import com.nooblabs.tmdb.domain.Configuration
import java.util.*
import javax.inject.Inject

class ConfigurationRepository @Inject constructor(
    private val configurationPersistenceSource: ConfigurationPersistenceSource,
    private val configurationNetworkSource: ConfigurationNetworkSource
) {

    suspend fun getSavedConfiguration() = configurationPersistenceSource.getPersistedConfiguration()

    suspend fun requestNewConfiguration(): Configuration {
        val configuration = configurationNetworkSource.getConfiguration().apply {
            timestamp = Date().time
        }
        configurationPersistenceSource.saveNewConfiguration(configuration)
        return configuration
    }

}

interface ConfigurationPersistenceSource {

    suspend fun getPersistedConfiguration(): Configuration?
    fun saveNewConfiguration(configuration: Configuration)
}

interface ConfigurationNetworkSource {

    suspend fun getConfiguration(): Configuration
}