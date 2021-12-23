package com.thahira.example.musicmvpapp.database

import androidx.room.Dao
import androidx.room.Query
import com.thahira.example.musicmvpapp.model.classic.Result
import io.reactivex.Single


@Dao
interface ResultDao {

    @Query("select * from Result")
    fun getClassic(): Single<List<Result>>

    @Query("select * from Result")
    fun getRock(): Single<List<com.thahira.example.musicmvpapp.model.rock.Result>>

    @Query("select * from Result")
    fun getPop(): Single<List<com.thahira.example.musicmvpapp.model.pop.Result>>
}