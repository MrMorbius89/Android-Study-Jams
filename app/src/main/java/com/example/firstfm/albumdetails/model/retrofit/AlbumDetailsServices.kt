package com.example.firstfm.albumdetails.model.retrofit

import com.example.firstfm.constants.CONSTANTS
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumDetailsServices {

    @GET("2.0/")
    suspend fun getAlbumDetails(@Query("method") method:String = "album.getinfo", @Query("artist") artist:String, @Query("album") album:String, @Query("api_key") apiKey:String = CONSTANTS.api_key, @Query("format") format:String = "json"):AlbumDetails

}