package com.thahira.example.musicmvpapp.di

import android.net.ConnectivityManager
import com.thahira.example.musicmvpapp.database.ResultDatabase
import com.thahira.example.musicmvpapp.presenters.*
import com.thahira.example.musicmvpapp.rest.MusicApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {

    @Provides
    fun provideRockPresenter(musicApi: MusicApi, connectivityManager: ConnectivityManager,resultDatabase: ResultDatabase
    ): IRockPresenter {
        return RockPresenter(musicApi,connectivityManager,resultDatabase)
    }

    @Provides
    fun provideClassicPresenter(musicApi: MusicApi, connectivityManager: ConnectivityManager,resultDatabase: ResultDatabase
    ): IClassicPresenter {
        return ClassicPresenter(musicApi,connectivityManager,resultDatabase)
    }

    @Provides
    fun providePopPresenter(musicApi: MusicApi, connectivityManager: ConnectivityManager,resultDatabase: ResultDatabase
    ): IPopPresenter {
        return PopPresenter(musicApi,connectivityManager,resultDatabase)
    }

}