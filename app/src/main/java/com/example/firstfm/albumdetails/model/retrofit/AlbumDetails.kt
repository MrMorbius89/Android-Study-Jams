package com.example.firstfm.albumdetails.model.retrofit

import com.example.firstfm.artists.model.retrofit.Image
import com.example.firstfm.genredetails.model.retrofit.Wiki

data class AlbumDetails (
    val album:Album
    )

data class Album(
    val name:String,
    val artist:String,
    val image:List<Image>,
    val tags:Tags,
    val wiki:Wiki
)

data class Tags(
    val tag:List<Tag>
)

data class Tag(
    val name:String
)