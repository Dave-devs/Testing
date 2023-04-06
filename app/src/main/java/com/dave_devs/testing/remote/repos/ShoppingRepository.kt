package com.dave_devs.testing.remote.repos

import androidx.lifecycle.LiveData
import com.dave_devs.testing.local.ShoppingItem
import com.dave_devs.testing.remote.resource_class.Resource
import com.dave_devs.testing.remote.response.ImageResponse

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun getTotalItemPrice(): LiveData<Float>

    fun getShoppingItem(): LiveData<List<ShoppingItem>>

    suspend fun searchForImage(searchQuery: String): Resource<ImageResponse>
}