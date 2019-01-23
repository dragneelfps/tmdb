package com.nooblabs.tmdb.usecase

import com.nooblabs.tmdb.data.TrendingMoviesRepository
import javax.inject.Inject

class RequestNewTrendingMovies @Inject constructor(private val trendingMoviesRepository: TrendingMoviesRepository) {

    suspend operator fun invoke() = trendingMoviesRepository.requestNewMovies()

}