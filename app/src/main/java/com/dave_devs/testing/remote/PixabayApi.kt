package com.dave_devs.testing.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = "hssssvss8wwttw6wvsu8853373v3"
    ): Response<ImageResponse>
}