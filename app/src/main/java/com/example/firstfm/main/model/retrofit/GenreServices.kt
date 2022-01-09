package com.example.firstfm.main.model.retrofit


import com.example.firstfm.constants.CONSTANTS
import retrofit2.http.GET
import retrofit2.http.Query



interface GenreServices {

    @GET("2.0/")
    suspend fun getGenres(@Query("method") method:String="tag.getTopTags", @Query("api_key") apiKey:String = CONSTANTS.api_key, @Query("format") format:String = "json"): TopGenres
}