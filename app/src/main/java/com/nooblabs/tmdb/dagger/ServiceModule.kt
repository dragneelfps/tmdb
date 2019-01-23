package com.nooblabs.tmdb.dagger

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nooblabs.tmdb.data.INetworkSource
import com.nooblabs.tmdb.data.IPersistenceSource
import com.nooblabs.tmdb.framework.RetrofitNetworkSource
import com.nooblabs.tmdb.framework.RoomPersistenceSource
import com.nooblabs.tmdb.network.API_KEY
import com.nooblabs.tmdb.network.BASE_URL
import com.nooblabs.tmdb.network.NetworkService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    fun configurationPersistenceSource(roomPersistenceSource: RoomPersistenceSource): IPersistenceSource =
        roomPersistenceSource

    @Provides
    fun configurationNetworkSource(retrofitNetworkSource: RetrofitNetworkSource): INetworkSource =
            retrofitNetworkSource

    @Provides
    @Singleton
    fun getRetrofit() : Retrofit {

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val queryInterceptor = Interceptor { chain ->
            var request = chain.request()
            val url = request.url().newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()
            request = request.newBuilder()
                .url(url)
                .build()
            chain.proceed(request)
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(queryInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun retrofitNetworkService(retrofit: Retrofit): NetworkService = retrofit.create(NetworkService::class.java)

}