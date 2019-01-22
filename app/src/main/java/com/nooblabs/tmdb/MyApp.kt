package com.nooblabs.tmdb

import android.app.Activity
import android.app.Application
import com.nooblabs.tmdb.dagger.*

class MyApp : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .roomModule(RoomModule(this))
            .serviceModule(ServiceModule())
            .build()
    }

    fun getAppComponent() = appComponent

}