package com.dave_devs.testing.remote

import com.google.gson.annotations.SerializedName

data class ImageResult(
    val comments: Int,
    val downloads: Int,
    val favorites: Int,
    val id: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val imageWidth: Int,
    val largeImageUrl: String,
    val likes: Int,
    val pageUrl: String,
    val previewHeight: Int,
    val previewUrl: String,
    val previewWidth: Int,
    val tags: String,
    val type: String,
    val user: String,
    @SerializedName("user_id")
    val userId: Int,
    val userImageUrl: String,
    val views: Int
)
