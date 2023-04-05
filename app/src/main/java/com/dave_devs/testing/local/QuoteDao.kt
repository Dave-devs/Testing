package com.dave_devs.testing.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: Quote)

    @Update
    suspend fun updateQuote(quote: Quote)

    @Query("DELETE FROM quote")
    suspend fun delete()

    @Query("SELECT * FROM quote")
    fun getQuotes(): LiveData<List<Quote>>

    @Query("SELECT * FROM quote WHERE id = :quoteId")
    suspend fun getQuotesById(quoteId: Int): Quote
}