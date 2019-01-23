package com.nooblabs.tmdb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nooblabs.tmdb.database.dao.ConfigurationDao
import com.nooblabs.tmdb.database.dao.TrendingMoviesDao
import com.nooblabs.tmdb.domain.Configuration
import com.nooblabs.tmdb.domain.TrendingMovie

@Database(entities = [Configuration::class, TrendingMovie::class], version = 1, exportSchema = false)
@TypeConverters(CustomTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun configurationDao(): ConfigurationDao

    abstract fun trendingMoviesDao(): TrendingMoviesDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: kotlin.run {
                Room.databaseBuilder(context, AppDatabase::class.java, "app").build()
                    .apply {
                        INSTANCE = this
                    }
            }
        }
    }

}