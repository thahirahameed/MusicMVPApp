package com.thahira.example.musicmvpapp.di

import com.thahira.example.musicmvpapp.MainActivity
import com.thahira.example.musicmvpapp.views.ClassicFragment
import com.thahira.example.musicmvpapp.views.PopFragment
import com.thahira.example.musicmvpapp.views.RockFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    PresenterModule::class
])


@Singleton
interface MusicComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(rockFragment: RockFragment)
    fun inject(classicFragment: ClassicFragment)
    fun inject(popFragment: PopFragment)
}