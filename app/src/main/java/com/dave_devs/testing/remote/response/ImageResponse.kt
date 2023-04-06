package com.dave_devs.testing.remote.response

data class ImageResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)