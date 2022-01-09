package com.example.firstfm.tracks.model

import com.example.firstfm.tracks.model.retrofit.TopTracks
import com.example.firstfm.tracks.model.retrofit.Tracks
import com.example.firstfm.tracks.model.retrofit.TracksServices
import javax.inject.Inject

class TracksRepository @Inject constructor(private val tracksServices: TracksServices){

    suspend fun getAllTracks(tag:String): TopTracks = tracksServices.getTracks(tag = tag)
}