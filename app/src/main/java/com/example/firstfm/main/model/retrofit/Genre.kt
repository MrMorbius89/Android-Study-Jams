package com.example.firstfm.main.model.retrofit

data class TopGenres (
    val toptags:TopTag
)

data class TopTag (
    val tag:List<Genre>
)

data class Genre (
    val name:String
)