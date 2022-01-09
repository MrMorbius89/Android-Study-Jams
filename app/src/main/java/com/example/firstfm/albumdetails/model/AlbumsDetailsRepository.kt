package com.example.firstfm.albumdetails.model

import com.example.firstfm.albumdetails.model.retrofit.AlbumDetails
import com.example.firstfm.albumdetails.model.retrofit.AlbumDetailsServices
import javax.inject.Inject

class AlbumsDetailsRepository @Inject constructor(private val albumsDetailsServices: AlbumDetailsServices) {

    suspend fun getAlbumInfo(artist:String, album:String): AlbumDetails = albumsDetailsServices.getAlbumDetails(album = album, artist = artist)
}