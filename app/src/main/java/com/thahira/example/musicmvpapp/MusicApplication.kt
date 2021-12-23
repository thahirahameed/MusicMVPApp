package com.thahira.example.musicmvpapp

import android.app.Application
import com.thahira.example.musicmvpapp.di.AppModule
import com.thahira.example.musicmvpapp.di.DaggerMusicComponent
import com.thahira.example.musicmvpapp.di.MusicComponent

class MusicApplication :Application() {
    override fun onCreate() {
        super.onCreate()

        //starting the dagger component upon app initialization
        musicComponent = DaggerMusicComponent
            .builder()
        // Create the app module to be used
            .appModule(AppModule(this))
                //build the dagger component
            .build()

    }

    companion object{
        lateinit var musicComponent: MusicComponent
    }
}