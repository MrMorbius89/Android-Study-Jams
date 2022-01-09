package com.example.firstfm.artistdetails.model.retrofit

import com.example.firstfm.albumdetails.model.retrofit.Tags
import com.example.firstfm.artists.model.retrofit.Image
import com.example.firstfm.genredetails.model.retrofit.Wiki


data class ArtistDetails (
    val name:String,
    val image:List<Image>,
    val stats:Stats,
    val tags:Tags,
    val bio:Wiki
    )

data class Stats(
        val listeners:String,
        val playcount:String

)