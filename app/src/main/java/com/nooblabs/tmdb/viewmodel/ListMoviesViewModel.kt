package com.nooblabs.tmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nooblabs.tmdb.domain.Configuration
import com.nooblabs.tmdb.domain.TrendingMovie
import com.nooblabs.tmdb.logd
import com.nooblabs.tmdb.usecase.GetConfiguration
import com.nooblabs.tmdb.usecase.GetTrendingMovies
import com.nooblabs.tmdb.usecase.RequestNewConfiguration
import com.nooblabs.tmdb.usecase.RequestNewTrendingMovies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class ListMoviesViewModel @Inject constructor(private val getConfiguration: GetConfiguration,
                                              private val requestNewConfiguration: RequestNewConfiguration,
                                              private val getTrendingMovies: GetTrendingMovies,
                                              private val requestNewTrendingMovies: RequestNewTrendingMovies
) : ViewModel() {

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

    fun getTrendingMovies(): LiveData<List<TrendingMovie>> {
        val trendingMoviesLiveData = MutableLiveData<List<TrendingMovie>>()
        ioScope.launch {
            val storedTrendingMovies = getTrendingMovies.invoke()
            if (storedTrendingMovies.isEmpty()) {
                logd("No trending movies stored. Getting it from network")
                val trendingMovies = requestNewTrendingMovies()
                logd("Got trending movies from network and updated the persistence storage. Using it.")
                trendingMoviesLiveData.postValue(trendingMovies)
            } else {
                logd("Stored trending movies found. Using them for now.")
                trendingMoviesLiveData.postValue(storedTrendingMovies)
                logd("Querying for updated trending movies.")
                val trendingMovies = requestNewTrendingMovies()
                logd("Got updated trending movies from network and updated the persistence storage. Using it.")
                trendingMoviesLiveData.postValue(trendingMovies)
            }
        }
        return trendingMoviesLiveData
    }
}

