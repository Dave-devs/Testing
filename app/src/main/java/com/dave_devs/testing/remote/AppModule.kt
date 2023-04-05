package com.dave_devs.testing.remote

import android.app.Application
import androidx.room.Room
import com.dave_devs.testing.local.ShoppingDao
import com.dave_devs.testing.local.ShoppingItemDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePixabayApi(): PixabayApi {
        return Retrofit.Builder()
            .baseUrl("https://pixabay.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PixabayApi::class.java)
    }

    @Provides
    @Singleton
    fun provideShoppingItemDatabase(app: Application): ShoppingItemDatabase {
        return Room.databaseBuilder(
            app,
            ShoppingItemDatabase::class.java,
            "shoppingDB"
        ).build()
    }

    @Provides
    @Singleton
    fun provideShoppingDao(database: ShoppingItemDatabase): ShoppingDao {
        return database.dao()
    }
}