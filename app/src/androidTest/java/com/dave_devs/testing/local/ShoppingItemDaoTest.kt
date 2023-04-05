package com.dave_devs.testing.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class) //This make sure this test run with JVM cos we run it on AndroidTest Directory.
@SmallTest
class ShoppingItemDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ShoppingItemDatabase
    private lateinit var dao: ShoppingDao

    @Before
    fun setUp() {
        //inMemoryDatabaseBuilder means it will only be saved for test case.
       database = Room.inMemoryDatabaseBuilder(
           ApplicationProvider.getApplicationContext(),
           ShoppingItemDatabase::class.java
       ).allowMainThreadQueries() //We explicitly want to allow this room database from the main thread.
           .build()

        dao = database.dao()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testInsertItem_expectedSingleItem() = runTest {
        val item = ShoppingItem(1, "Banana",5, 3.00f, "https://fakeurl.com")
        dao.insertShoppingItem(item)

        val result = dao.getShoppingItem().getOrAwaitValue()

        assertThat(result).contains(item)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testDeleteItem_expectedNoItem() = runTest {
        val item = ShoppingItem(1, "Banana",5, 3.00f, "https://fakeurl.com")
        dao.insertShoppingItem(item)
        dao.deleteShopping(item)

        val result = dao.getShoppingItem().getOrAwaitValue()

        assertThat(result).doesNotContain(item)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetItemTotalSum_returnItemTotalSum() = runTest {
        val item1 = ShoppingItem(1, "Banana",5, 3.00f, "https://fakeurl.com")
        val item2 = ShoppingItem(2, "Orange",7, 24.99f, "https://fakeurl.com")
        val item3 = ShoppingItem(3, "Bacon",2, 9.99f, "https://fakeurl.com")

        dao.insertShoppingItem(item1)
        dao.insertShoppingItem(item2)
        dao.insertShoppingItem(item3)

        val sum = dao.getTotalItemPrice().getOrAwaitValue()

        assertThat(sum).isEqualTo(5 * 3.00f + 7 * 24.99f + 2 * 9.99f)
    }

    @After
    fun tearDown() {
        database.close()
    }
}