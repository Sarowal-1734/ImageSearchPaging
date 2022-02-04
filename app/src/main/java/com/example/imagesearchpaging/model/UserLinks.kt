package com.example.imagesearchpaging.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserLinks(
    val html: String,
    val likes: String,
    val photos: String,
    val self: String
) : Parcelable