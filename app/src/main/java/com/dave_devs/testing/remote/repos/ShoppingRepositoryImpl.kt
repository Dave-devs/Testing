package com.dave_devs.testing.remote.repos

import androidx.lifecycle.LiveData
import com.dave_devs.testing.local.ShoppingDao
import com.dave_devs.testing.local.ShoppingItem
import com.dave_devs.testing.repos.PixabayApi
import com.dave_devs.testing.remote.resource_class.Resource
import com.dave_devs.testing.remote.response.ImageResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ShoppingRepositoryImpl @Inject constructor(
    private val api: com.dave_devs.testing.repos.PixabayApi,
    private val dao: ShoppingDao
): ShoppingRepository {

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
       return dao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        return dao.deleteShopping(shoppingItem)
    }

    override fun getTotalItemPrice(): LiveData<Float> {
        return dao.getTotalItemPrice()
    }

    override fun getShoppingItem(): LiveData<List<ShoppingItem>> {
        return dao.getShoppingItem()
    }

    override suspend fun searchForImage(searchQuery: String): Resource<ImageResponse> {
        return try {
            val response = api.searchForImage(searchQuery)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("An unknown error occurred")
            } else {
                Resource.Error("Unknown error occurred")
            }
        } catch (e: HttpException) {
            Resource.Error("Couldn't reach server check your internet connection")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach server check your internet connection")
        }
    }


}