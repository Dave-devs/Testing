package com.dave_devs.testing.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Quote::class],
    version = 1,
    exportSchema = true
)
abstract class QuoteDatabase: RoomDatabase() {
    abstract fun dao(): QuoteDao
}