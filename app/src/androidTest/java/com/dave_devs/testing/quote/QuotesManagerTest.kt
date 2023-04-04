package com.dave_devs.testing.quote

import android.content.Context
import android.content.res.AssetManager
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations
import java.io.FileNotFoundException

class QuotesManagerTest {

    private lateinit var quotesManager: QuotesManager

    @Mock
    private lateinit var context: Context
    @Mock
    private lateinit var assetManager: AssetManager

    @Before
    fun setUp() {
        //For Mock
        MockitoAnnotations.initMocks(this)
        //For Normal AndroidTest
        quotesManager = QuotesManager()
    }

    //Mock
    @Test
    fun test() {
        val testStream = QuotesManagerTest::class.java.getResourceAsStream("/quotes.json")
        doReturn(assetManager).`when`(context).assets
        Mockito.`when`(context.assets.open(anyString())).thenReturn(testStream)

        val sut = QuotesManager()
        sut.populateQuoteFromAssets(context, "")
        val quote = sut.getCurrentQuote()

        assertThat(quote.text).isEqualTo("Genius is one percent inspiration and ninety-nine percent perspiration.")
    }

    //Normal AndroidTest
    @Test(expected = FileNotFoundException::class)
    fun populateQuoteFromAssets() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        quotesManager.populateQuoteFromAssets(context, "")
    }

    @Test(expected = JsonSyntaxException::class)
    fun populateQuoteFromAssets_InvalidJson_returnException() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        quotesManager.populateQuoteFromAssets(context, "malformed.json")
    }

    @Test
    fun populateQuoteFromAssets_ValidJson_returnCount() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        quotesManager.populateQuoteFromAssets(context, "quotes.json")
        assertThat(quotesManager.quoteList.size).isEqualTo(9)
        //assertEquals(7, quotesManager.quoteList.size)
    }

    @Test
    fun getCurrentQuotes() {
        quotesManager.populateQuotes(
            arrayOf(
                Quote("This is first quote","Author1"),
                Quote("This is first quote","Author2"),
                Quote("This is first quote","Author3"),
                Quote("This is first quote","Author4"),
            ))
        val quote = quotesManager.getCurrentQuote()
        assertThat("Author1").isEqualTo(quote.author)
    }

    @Test
    fun getNextQuotes() {
        quotesManager.populateQuotes(
            arrayOf(
                Quote("This is first quote","Author1"),
                Quote("This is first quote","Author2"),
                Quote("This is first quote","Author3"),
                Quote("This is first quote","Author4"),
            ))
        val quote = quotesManager.getNextQuote()
        assertThat("Author2").isEqualTo(quote.author[1])
    }

    @Test
    fun getPreviousQuotes() {
        quotesManager.populateQuotes(
            arrayOf(
                Quote("This is first quote","Author1"),
                Quote("This is first quote","Author2"),
                Quote("This is first quote","Author3"),
                Quote("This is first quote","Author4"),
            ))
        val quote = quotesManager.getPreviousQuote()
        assertThat("Author1").isEqualTo(quote.author)
    }
}