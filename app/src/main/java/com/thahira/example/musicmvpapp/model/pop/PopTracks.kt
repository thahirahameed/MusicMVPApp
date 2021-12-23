package com.thahira.example.musicmvpapp.model.pop


import com.google.gson.annotations.SerializedName

data class PopTracks(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val results: List<Result>
)