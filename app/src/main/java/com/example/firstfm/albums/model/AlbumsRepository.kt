package com.example.firstfm.albums.model

import com.example.firstfm.albums.model.retrofit.Albums
import com.example.firstfm.albums.model.retrofit.AlbumsServices
import com.example.firstfm.albums.model.retrofit.TopAlbums
import javax.inject.Inject

class AlbumsRepository @Inject constructor(private val albumsServices: AlbumsServices){

    suspend fun getAllAlbums(tag:String):TopAlbums = albumsServices.getTracks(tag = tag)
}