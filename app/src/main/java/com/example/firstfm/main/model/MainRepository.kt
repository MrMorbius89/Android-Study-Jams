package com.example.firstfm.main.model

import com.example.firstfm.main.model.retrofit.GenreServices
import com.example.firstfm.main.model.retrofit.TopGenres
import javax.inject.Inject

class MainRepository @Inject constructor(private val genreServices:GenreServices) {

    suspend fun getAllGenres():TopGenres {
        return genreServices.getGenres()
    }

}