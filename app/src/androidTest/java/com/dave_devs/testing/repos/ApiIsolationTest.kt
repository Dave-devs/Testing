package com.dave_devs.testing.repos

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ApiIsolationTest: TestCase() {

    @Test
    fun testSearchForImage_returnSuccess() {
        val api = TestApiImplementation.providePixabayApiTest()
        val result = runBlocking {
            api.searchForImage("oceans")
        }

        assertThat(result.isSuccessful).isTrue()
    }

    @Test
    fun testSearchForImage_returnBodyNotEmpty() {
        val api = TestApiImplementation.providePixabayApiTest()
        val result = runBlocking {
            api.searchForImage("dogs")
        }

        assertThat(result.body()).isNotNull()
    }
}