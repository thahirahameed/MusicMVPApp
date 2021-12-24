package com.thahira.example.musicmvpapp.rest

import com.thahira.example.musicmvpapp.model.Tracks
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface MusicApi {

    //This will retrieve all the music details from Music

    @GET(SEARCH)
    fun retrieveClassic(
        @Query("term") term:String = MUSIC_CLASSIC,
        @Query("amp;media")media:String = MEDIA_TYPE,
        @Query("amp;entity")entity:String = ENTITY_TYPE,
        @Query("amp;limit")limit:String = LIMIT_50

    ): Single<Tracks>

    @GET(SEARCH)
    fun retrieveRock(
        @Query("term")term:String = MUSIC_ROCK,
        @Query("amp;media")media:String = MEDIA_TYPE,
        @Query("amp;entity")entity:String = ENTITY_TYPE,
        @Query("amp;limit")limit:String = LIMIT_50

    ): Single<Tracks>

    @GET(SEARCH)
    fun retrievePop(
        @Query("term")term:String = MUSIC_POP,
        @Query("amp;media")media:String = MEDIA_TYPE,
        @Query("amp;entity")entity:String = ENTITY_TYPE,
        @Query("amp;limit")limit:String = LIMIT_50

    ): Single<Tracks>


    companion object{

        const val BASE_URL ="https://itunes.apple.com/"

        private const val SEARCH ="search"

        const val MEDIA_TYPE ="music"
        const val ENTITY_TYPE ="song"
        const val LIMIT_50 ="50"

        const val MUSIC_CLASSIC ="classic"
        const val MUSIC_ROCK ="rock"
        const val MUSIC_POP ="pop"
    }
}