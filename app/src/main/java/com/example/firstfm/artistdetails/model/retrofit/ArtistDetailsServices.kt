package com.example.firstfm.artistdetails.model.retrofit

import com.example.firstfm.constants.CONSTANTS
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistDetailsServices {

    @GET("2.0/")
    suspend fun <T> getArtistDetails(@Query("method") method:String = "artist.getinfo", @Query("artist") artist:String, @Query("api_key") apiKey:String = CONSTANTS.api_key, @Query("format") format:String = "json"):T

}