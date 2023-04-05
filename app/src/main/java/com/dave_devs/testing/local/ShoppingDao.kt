package com.dave_devs.testing.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    @Delete
    suspend fun deleteShopping(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM shopping_item")
    fun getShoppingItem(): LiveData<List<ShoppingItem>>

    @Query("SELECT SUM(price * amount) FROM shoppingItem")
    fun getTotalItemPrice(): LiveData<Float>
}