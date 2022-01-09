package com.example.firstfm.tracks.model.retrofit

import com.example.firstfm.constants.CONSTANTS
import retrofit2.http.GET
import retrofit2.http.Query

interface TracksServices {

    @GET("2.0/")
    suspend fun getTracks(@Query("method") method:String = "tag.gettoptracks", @Query("tag") tag:String, @Query("api_key") apiKey:String = CONSTANTS.api_key, @Query("format") format:String = "json"):TopTracks
}