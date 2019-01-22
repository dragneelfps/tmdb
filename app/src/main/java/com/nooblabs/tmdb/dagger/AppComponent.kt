package com.nooblabs.tmdb.dagger

import com.nooblabs.tmdb.view.ListMoviesActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [], modules = [AppModule::class, RoomModule::class, ServiceModule::class])
interface AppComponent {

    fun inject(listMoviesActivity: ListMoviesActivity)

}