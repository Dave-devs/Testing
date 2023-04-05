package com.dave_devs.testing.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ShoppingItem::class],
    version = 1,
    exportSchema = false
)
abstract class ShoppingItemDatabase: RoomDatabase() {

    abstract fun dao(): ShoppingDao
}