package com.nooblabs.tmdb.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nooblabs.tmdb.domain.Configuration

@Dao
interface ConfigurationDao {

    @Insert
    fun insertConfiguration(configuration: Configuration)

    @Query("DELETE FROM configuration")
    fun deleteAll()

    @Query("SELECT * FROM configuration")
    fun getAllConfigurations(): List<Configuration>

}