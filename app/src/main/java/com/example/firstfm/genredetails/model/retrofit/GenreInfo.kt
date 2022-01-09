package com.example.firstfm.genredetails.model.retrofit

data class GenreInfo(
    val tag: InfoTag
)

data class InfoTag(
    val name:String,
    val wiki: Wiki
)

data class Wiki(
    val summary: String
)
