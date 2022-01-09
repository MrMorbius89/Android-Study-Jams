package com.example.firstfm.tracks.model.retrofit

import com.example.firstfm.albums.model.retrofit.Artist
import com.example.firstfm.artists.model.retrofit.Image

data class TopTracks (
        val tracks:TracksList
        )

data class TracksList(
        val track:List<Tracks>
)

data class Tracks(
        val name:String,
        val artist:Artist,
        val image:List<Image>
)