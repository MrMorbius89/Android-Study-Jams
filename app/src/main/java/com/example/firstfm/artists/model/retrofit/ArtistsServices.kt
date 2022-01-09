package com.example.firstfm.artists.model.retrofit


import com.example.firstfm.constants.CONSTANTS
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistsServices {

    @GET("2.0/")
    suspend fun getArtists(@Query("method") method:String = "tag.gettopartists", @Query("tag") tag:String, @Query("api_key") apiKey:String = CONSTANTS.api_key, @Query("format") format:String = "json"):TopArtists
}