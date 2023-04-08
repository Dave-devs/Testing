package com.dave_devs.testing.util

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class SampleTest {
    private lateinit var sample: Sample

    @Before
    fun setUp() {
        sample = Sample()
    }

    @Test
    fun isPalindrome() {
        val result = sample.isPalindrome("hello")

        assertThat(result).isEqualTo(false)
    }

    @Test
    fun isPalindrome_inputString_level_expectedTrue() {
        val result = sample.isPalindrome("level")

        assertThat(result).isEqualTo(true)
    }
}