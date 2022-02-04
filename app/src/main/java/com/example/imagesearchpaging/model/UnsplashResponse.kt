package com.example.imagesearchpaging.model


data class UnsplashResponse(
    val total: Int,
    val total_pages: Int,
    val results: MutableList<UnsplashPhoto>
)
