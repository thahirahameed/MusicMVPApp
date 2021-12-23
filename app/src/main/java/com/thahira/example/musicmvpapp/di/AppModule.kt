package com.thahira.example.musicmvpapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import androidx.room.Room
import com.thahira.example.musicmvpapp.database.ResultDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideConnectivityManager(context: Context) : ConnectivityManager{
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @Singleton
    fun provideResultDatabase(context: Context) : ResultDatabase{
        return Room.databaseBuilder(
            context,
            ResultDatabase:: class.java,
            "Songs-DB"
        ).build()
    }
}