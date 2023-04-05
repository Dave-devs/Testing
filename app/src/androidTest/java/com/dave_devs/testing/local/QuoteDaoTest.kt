package com.dave_devs.testing.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class QuoteDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var quoteDatabase: QuoteDatabase
    private lateinit var quoteDao: QuoteDao

    @Before
    fun setUp() {

        quoteDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            QuoteDatabase::class.java
        ).allowMainThreadQueries().build()

        quoteDao = quoteDatabase.dao()

    }

    @Test
    fun insertQuote_expectedSingleQuote() = runBlocking {
        val quote = Quote(0, "This is text quote", "Author1")
        //Insert Quote
        quoteDao.insertQuote(quote)

        val result = quoteDao.getQuotes().getOrAwaitValue()

        assertThat(result).contains(quote)
    }

    @Test
    fun deleteQuote_expectedNoResults() = runBlocking {
        val quote = Quote(0, "This is text quote", "Author2")
        //Insert Quote
        quoteDao.insertQuote(quote)
        //Delete Quote
        quoteDao.delete()

        val result = quoteDao.getQuotes().getOrAwaitValue()

        assertThat(result).doesNotContain(quote)
    }

    @Test
    fun getQuote_returnQuoteById() = runBlocking {
        val quote = Quote(0, "This is text quote", "Author3")
        //Insert Quote
        quoteDao.insertQuote(quote)

        val result = quoteDao.getQuotesById(0)

        assertThat(result).isEqualTo(quote)
    }

    @After
    fun tearDown() {
        quoteDatabase.close()
    }
}