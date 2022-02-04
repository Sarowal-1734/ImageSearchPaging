package com.example.imagesearchpaging.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UnsplashPhoto(
    val id: String,
    val user: User,
    val description: String,
    val created_at: String,
    val likes: Int,
    val links: PhotoLinks,
    val urls: Urls
) : Parcelable