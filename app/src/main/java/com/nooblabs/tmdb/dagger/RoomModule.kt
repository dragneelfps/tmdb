package com.nooblabs.tmdb.dagger

import android.app.Application
import androidx.room.Room
import com.nooblabs.tmdb.database.AppDatabase
import com.nooblabs.tmdb.database.dao.ConfigurationDao
import com.nooblabs.tmdb.database.dao.TrendingMoviesDao
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class RoomModule @Inject constructor(private val application: Application) {

    private var database: AppDatabase =
        Room.databaseBuilder(application, AppDatabase::class.java, "app-db").build()

    @Provides
    @Singleton
    fun appDatabase(): AppDatabase = database

    @Provides
    @Singleton
    fun configurationDao(database: AppDatabase): ConfigurationDao = database.configurationDao()

    @Provides
    @Singleton
    fun trendingMoviesDao(database: AppDatabase): TrendingMoviesDao = database.trendingMoviesDao()

}