package com.thahira.example.musicmvpapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.thahira.example.musicmvpapp.adapters.FragmentsAdapter
import com.thahira.example.musicmvpapp.databinding.ActivityMainBinding
import com.thahira.example.musicmvpapp.di.DaggerMusicComponent

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MusicApplication.musicComponent.inject(this)

        binding.musicAlbumContainer.adapter = FragmentsAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.menuBar, binding.musicAlbumContainer) { tab, position ->
            when(position){
                0 -> {
                    tab.text = "Classic"
                }
                1 -> {
                    tab.text = "Pop"
                }
                else -> {
                    tab.text = "Rock"
                }
            }
        }.attach()
    }
}