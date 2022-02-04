package com.example.imagesearchpaging.api

import com.example.imagesearchpaging.model.UnsplashResponse
import com.example.imagesearchpaging.util.Constants.Companion.ACCESS_TOKEN
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {
    @Headers("Accept-Version: v1", "Authorization: Client-ID $ACCESS_TOKEN")
    @GET("/search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): UnsplashResponse
}