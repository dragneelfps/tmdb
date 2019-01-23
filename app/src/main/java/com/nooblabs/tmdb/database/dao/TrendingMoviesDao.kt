package com.nooblabs.tmdb.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nooblabs.tmdb.domain.TrendingMovie

@Dao
interface TrendingMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTrendingMovies(movies: List<TrendingMovie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrendingMovie(movie: TrendingMovie)

    @Query("SELECT * FROM trendingmovie")
    fun getAllTrendingMovies(): List<TrendingMovie>
}