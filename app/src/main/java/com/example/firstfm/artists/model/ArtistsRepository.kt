package com.example.firstfm.artists.model

import com.example.firstfm.artists.model.retrofit.ArtistsServices
import com.example.firstfm.artists.model.retrofit.TopArtists
import javax.inject.Inject

class ArtistsRepository @Inject constructor(private val artistsServices: ArtistsServices) {
    suspend fun getAllArtists(tag:String): TopArtists = artistsServices.getArtists(tag = tag)
}