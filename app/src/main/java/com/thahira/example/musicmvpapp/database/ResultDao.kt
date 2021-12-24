package com.thahira.example.musicmvpapp.database

import androidx.room.Dao
import androidx.room.Query
import com.thahira.example.musicmvpapp.model.Result
import io.reactivex.Single


@Dao
interface ResultDao {

    @Query("SELECT * from Result")
    fun getClassic(): Single<List<Result>>

    @Query("SELECT * from Result")
    fun getRock(): Single<List<Result>>

    @Query("SELECT * from Result")
    fun getPop(): Single<List<Result>>
}