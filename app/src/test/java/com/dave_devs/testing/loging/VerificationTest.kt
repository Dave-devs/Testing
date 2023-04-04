package com.dave_devs.testing.loging

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class VerificationTest {

    private lateinit var verification: Verification

    @Before
    fun setUp() {
        verification = Verification()
    }

    @Test
    fun validatePassword_emptyField() {
        val sut = verification.validatePassword( " ")
        assertThat(sut).isEqualTo( "Password can not be empty")
    }

    @Test
    fun validatePassword_lessThanTwenty() {
        val sut = verification.validatePassword( "password/validation/1234")
        assertThat(sut).isEqualTo( "Input should be less than twenty")
    }

    @Test
    fun validatePassword_greaterThanEight() {
        val sut = verification.validatePassword( "pass")
        assertThat(sut).isEqualTo( "Input should be greater than eight")
    }

    @Test
    fun validatePassword_validPasswordInput() {
        val sut = verification.validatePassword( "password123")
        assertThat(sut).isEqualTo( "Valid password input")
    }
}