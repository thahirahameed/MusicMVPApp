package com.thahira.example.musicmvpapp.model


import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val results: List<Result>
)