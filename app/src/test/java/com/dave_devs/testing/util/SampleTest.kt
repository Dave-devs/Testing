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
    fun isPalladium() {
        val result = sample.isPalladium("hello")

        assertThat(result).isEqualTo(false)
    }

    @Test
    fun isPalladium_inputString_level_expectedTrue() {
        val result = sample.isPalladium("level")

        assertThat(result).isEqualTo(true)
    }
}