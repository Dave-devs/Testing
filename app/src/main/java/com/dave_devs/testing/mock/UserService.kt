package com.dave_devs.testing.mock

class UserService(private val userRepository: UserRepository) {

    fun loginUser(email: String, password: String): String {
        val status = userRepository.loginUser(email, password)
        return when(status) {
            LoginStatus.InvalidUser -> "User does not exist"
            LoginStatus.InvalidPassword -> "Invalid password"
            LoginStatus.UnknownError -> "Unknown error occurred"
            LoginStatus.Success -> "Logged in successfully"
        }
    }
}