package com.dave_devs.testing.quote

import android.content.Context
import com.google.gson.Gson

class QuotesManager {

    var quoteList = emptyArray<Quote>()
    private var currentQuoteIndex = 0

    fun populateQuoteFromAssets(context: Context, fileName: String) {
        val inputStream = context.assets.open(fileName) //Get access to the asset directory(quote.json) and open
        val size: Int = inputStream.available() //Get all available data/byte data in asset directory(quote.json)
        val buffer = ByteArray(size) //Creates a new array of the specified size, with all elements initialized to zero.
        inputStream.read(buffer) //Reads some number of bytes from the input stream and stores them into the buffer array b(int).
        inputStream.close() //Closes this input stream and releases any system resources associated with the stream.
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        quoteList = gson.fromJson(json, Array<Quote>::class.java)
    }

    fun populateQuotes(quotes: Array<Quote>) {
        quoteList = quotes
    }

    fun getCurrentQuote(): Quote {
        return quoteList[currentQuoteIndex]
    }

    fun getNextQuote(): Quote {
        if (currentQuoteIndex == quoteList.size -1) return quoteList[currentQuoteIndex]
        return quoteList[++currentQuoteIndex]
    }

    fun getPreviousQuote(): Quote {
        if (currentQuoteIndex == 0) return quoteList[currentQuoteIndex]
        return quoteList[--currentQuoteIndex]
    }
}