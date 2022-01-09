package com.example.firstfm.genredetails.model.retrofit

import com.example.firstfm.constants.CONSTANTS
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreInfoServices {

    @GET("2.0/")
    suspend fun getGenreInfo(@Query("method") method:String="tag.getinfo", @Query("tag") tag:String, @Query("api_key") apiKey:String=CONSTANTS.api_key, @Query("format") format:String = "json" ) : GenreInfo
}