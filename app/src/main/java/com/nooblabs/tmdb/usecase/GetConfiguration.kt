package com.nooblabs.tmdb.usecase

import com.nooblabs.tmdb.data.ConfigurationRepository
import javax.inject.Inject

class GetConfiguration @Inject constructor(private val configurationRepository: ConfigurationRepository) {

    suspend operator fun invoke() = configurationRepository.getSavedConfiguration()

}