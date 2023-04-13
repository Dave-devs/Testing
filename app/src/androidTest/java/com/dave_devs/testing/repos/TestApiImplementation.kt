package com.dave_devs.testing.repos

import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TestApiImplementation {

    @Provides
    fun providePixabayApiTest(): PixabayApiTest {
        return Retrofit.Builder()
            .baseUrl("https://pixabayapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PixabayApiTest::class.java)
    }
}