package com.example.firstfm.artistdetails.model

import com.example.firstfm.artistdetails.model.retrofit.ArtistAlbums
import com.example.firstfm.artistdetails.model.retrofit.ArtistDetails
import com.example.firstfm.artistdetails.model.retrofit.ArtistDetailsServices
import com.example.firstfm.artistdetails.model.retrofit.ArtistTracks
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

class ArtistDetailsRepository @Inject constructor(private val artistDetailsServices: ArtistDetailsServices){

    suspend fun getArtistInfo(artist:String):ArtistDetails = artistDetailsServices.getArtistDetails(artist=artist)
    suspend fun getArtistTracks(artist: String):ArtistTracks = artistDetailsServices.getArtistDetails(artist=artist,method = "artist.gettoptracks" )
    suspend fun getArtistAlbums(artist: String):ArtistAlbums = artistDetailsServices.getArtistDetails(artist = artist, method = "artist.gettopalbums")

}