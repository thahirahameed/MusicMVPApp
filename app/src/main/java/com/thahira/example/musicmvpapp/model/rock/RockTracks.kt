package com.thahira.example.musicmvpapp.model.rock


import com.google.gson.annotations.SerializedName

data class RockTracks(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val results: List<Result>
)