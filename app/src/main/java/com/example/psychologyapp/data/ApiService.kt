package com.example.psychologyapp.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("client_id") clientId: String
    ): UnsplashResponse

    @GET("meditations")
    suspend fun getMeditations(): MeditationResponse

    @GET("articles")
    suspend fun getArticles(): ArticleResponse
}