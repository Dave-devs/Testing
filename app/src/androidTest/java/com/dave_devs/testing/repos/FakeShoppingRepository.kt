package com.dave_devs.testing.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dave_devs.testing.local.ShoppingItem
import com.dave_devs.testing.remote.repos.ShoppingRepository
import com.dave_devs.testing.remote.resource_class.Resource
import com.dave_devs.testing.remote.response.ImageResponse

/*
Class to test ViewModel cos ViewModel take repository
as a parameter to pass data. This FakeRepository will
simulate our real Repository
*/
class FakeShoppingRepository: ShoppingRepository {

    //Simulate ShoppingItem class
    private var shoppingItems = mutableListOf<ShoppingItem>()

    /* Livedata object to simulate the behaviour of
    `GetShoppingItem` and `GetTotalItemPrice` in
    shoppingRepository which also return LiveData Object
    */
    private var getShoppingItems = MutableLiveData<List<ShoppingItem>>(shoppingItems)
    private var getTotalItemsPrice = MutableLiveData<Float>()

    //Code to send a network response either error or success
    private var shouldReturnNetworkError = false
    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    //Function to refresh LiveData
    private fun refreshLiveData() {
        getShoppingItems.postValue(shoppingItems)
        getTotalItemsPrice.postValue(getTotalPrice())
    }
    private fun getTotalPrice(): Float {
        return shoppingItems.sumOf { it.price.toDouble() }.toFloat()
    }


    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.add(shoppingItem)
        refreshLiveData()
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.remove(shoppingItem)
        refreshLiveData()
    }

    override fun getTotalItemPrice(): LiveData<Float> {
        return getTotalItemsPrice
    }

    override fun getShoppingItem(): LiveData<List<ShoppingItem>> {
        return getShoppingItems
    }

    override suspend fun searchForImage(searchQuery: String): Resource<ImageResponse> {
        return if (shouldReturnNetworkError) {
            Resource.Error("Error Message")
        } else {
            Resource.Success(
                ImageResponse(listOf(), 0, 0)
            )
        }
    }
}