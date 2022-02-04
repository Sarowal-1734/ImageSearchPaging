package com.example.imagesearchpaging.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoLinks(
    val download: String,
    val html: String
) : Parcelable