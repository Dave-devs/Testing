package com.dave_devs.testing.loging

class Verification {

    fun validatePassword(input: String) = when {
        input.isBlank() -> {
            "Password can not be empty"
        }
        input.length > 20 -> {
            "Input should be less than twenty"
        }
        input.length < 8 -> {
            "Input should be greater than eight"
        }
        else -> {
            "Valid password input"
        }
    }
}