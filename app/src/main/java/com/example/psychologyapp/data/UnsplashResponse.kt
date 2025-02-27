package com.example.psychologyapp.data

data class UnsplashResponse(
    val results: List<Photo>
)

data class Photo(
    val urls: Urls
)

data class Urls(
    val regular: String
)