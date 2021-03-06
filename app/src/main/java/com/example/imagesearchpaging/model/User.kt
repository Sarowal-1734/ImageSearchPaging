package com.example.imagesearchpaging.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val first_name: String,
    val id: String,
    val instagram_username: String,
    val last_name: String,
    val links: UserLinks,
    val name: String,
    val portfolio_url: String,
    val profile_image: ProfileImage,
    val twitter_username: String,
    val username: String
) : Parcelable