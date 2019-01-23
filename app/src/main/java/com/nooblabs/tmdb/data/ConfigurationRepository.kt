package com.nooblabs.tmdb.data

import com.nooblabs.tmdb.domain.Configuration
import java.util.*
import javax.inject.Inject

class ConfigurationRepository @Inject constructor(
    private val persistenceSource: IPersistenceSource,
    private val networkSource: INetworkSource
) {

    suspend fun getSavedConfiguration() = persistenceSource.getPersistedConfiguration()

    suspend fun requestNewConfiguration(): Configuration {
        val configuration = networkSource.getConfiguration().apply {
            timestamp = Date().time
        }
        persistenceSource.saveNewConfiguration(configuration)
        return configuration
    }

}
