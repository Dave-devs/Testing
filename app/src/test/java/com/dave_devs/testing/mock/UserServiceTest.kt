package com.dave_devs.testing.mock

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserServiceTest {
    @Mock
    private lateinit var userRepository: UserRepository
    private lateinit var userService: UserService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(
            userRepository.loginUser(anyString(), anyString())
        ).thenReturn(LoginStatus.InvalidUser)

        Mockito.`when`(
            userRepository.loginUser("john@gmail.com", "asysfs2d6")
        ).thenReturn(LoginStatus.Success)

        userService = UserService(userRepository)
    }

    @Test
    fun testUserService_returnInvalidUser() {
        val sut = userService
        val status = sut.loginUser("abc@gmail.com", "111111")
        assertThat(status).isEqualTo("User does not exist")
    }

    @Test
    fun testUserService_returnLoginSuccess() {
        val sut = userService
        val status = sut.loginUser("john@gmail.com", "asysfs2d6")
        assertThat(status).isEqualTo("Logged in successfully")
    }
}