package com.example.firstfm.genredetails.model

import com.example.firstfm.genredetails.model.retrofit.GenreInfoServices
import javax.inject.Inject

class GenreDetailsRepository @Inject constructor(private val genreInfoServices: GenreInfoServices) {

    suspend fun getGenreInfo(tag:String) = genreInfoServices.getGenreInfo(tag = tag)
}