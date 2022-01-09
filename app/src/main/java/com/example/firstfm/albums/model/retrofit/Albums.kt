package com.example.firstfm.albums.model.retrofit

import com.example.firstfm.artists.model.retrofit.Image

data class TopAlbums (
        val albums:AlbumsList
        )

data class AlbumsList(
        val album:List<Albums>
)

data class Albums(
        val name:String,
        val artist:Artist,
        val image:List<Image>
)

data class Artist(
        val name: String
)