package com.dave_devs.testing.mock

sealed class LoginStatus {
    object InvalidUser: LoginStatus()
    object InvalidPassword: LoginStatus()
    object UnknownError: LoginStatus()
    object Success: LoginStatus()
}