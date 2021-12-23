package com.thahira.example.musicmvpapp.model.classic


import com.google.gson.annotations.SerializedName

data class ClassicTracks(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val results: List<Result>
)