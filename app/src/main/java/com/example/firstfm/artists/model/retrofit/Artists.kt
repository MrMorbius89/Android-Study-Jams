package com.example.firstfm.artists.model.retrofit

import com.google.gson.annotations.SerializedName


data class TopArtists (
        val topartists:ArtistsList
        )

data class ArtistsList(
        val artist:List<Artists>
)

data class Artists(
        val name:String,
        val image:List<Image>
)

data class Image(
        @SerializedName("#text")
        val url:String
)