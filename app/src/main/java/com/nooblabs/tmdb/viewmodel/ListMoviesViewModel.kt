package com.nooblabs.tmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nooblabs.tmdb.domain.Configuration
import com.nooblabs.tmdb.logd
import com.nooblabs.tmdb.usecase.GetConfiguration
import com.nooblabs.tmdb.usecase.RequestNewConfiguration
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

class ListMoviesViewModel @Inject constructor(private val getConfiguration: GetConfiguration,
                                              private val requestNewConfiguration: RequestNewConfiguration) : ViewModel() {

    private val ioJob = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + ioJob)

    override fun onCleared() {
        super.onCleared()
        ioJob.cancel()
    }

    fun getConfiguration(): LiveData<Configuration> {
        val configurationLiveData = MutableLiveData<Configuration>()
        ioScope.launch {

            val storedConfiguration = getConfiguration.invoke()

            if (storedConfiguration == null) {
                logd("No stored configuration found")
                val configuration = getAndInsertConfiguration()
                configurationLiveData.postValue(configuration)
            } else {
                logd("Stored configuration found. Checking if its old.")
                val now = Date().time
                if (now - storedConfiguration.timestamp!! > 3 * 24 * 60 * 60 * 1000) {
                    logd("Old configuration detected. Retrieving new configuration from the server")
                    val configuration = getAndInsertConfiguration()
                    configurationLiveData.postValue(configuration)
                } else {
                    logd("Stored configuration is up-to-date. Using it.")
                    configurationLiveData.postValue(storedConfiguration)
                }
            }

        }
        return configurationLiveData
    }

    private suspend fun getAndInsertConfiguration(): Configuration {
        return requestNewConfiguration()
    }
}

