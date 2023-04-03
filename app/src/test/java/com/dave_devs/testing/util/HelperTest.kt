package com.dave_devs.testing.util

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class HelperTest {

    private lateinit var helper: Helper

    @Before
    fun setUp() {
        helper = Helper()
    }

    @Test
    fun isNumberPositive_returnTrue() {
        val result = helper.isNumberPositive(2)
        assertThat(result).isTrue()
    }

    @Test
    fun isNumberPositive_returnFalse() {
        val result = helper.isNumberPositive(-2)
        assertThat(result).isFalse()
    }

    @Test
    fun signUp_emptyFields_returnTrue() {
        val result = helper.signUp(" "," "," ")
        assertThat(result).isTrue()
    }

    @Test
    fun signUp_validFields_returnTrue() {
        val result = helper.signUp("Peter", "John", "Smith")
        assertThat(result).isTrue()
    }
}