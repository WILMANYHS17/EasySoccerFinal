package com.example.easysoccer1.ui.viewmodel

import com.example.easysoccer1.data.models.Users
import com.example.easysoccer1.domain.UsersUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*



class ForgotPasswordViewModelTest {
    private var usersUseCase: UsersUseCase = mockk(relaxed = true)
    private lateinit var forgotPasswordViewModel: ForgotPasswordViewModel

    @Before
    fun setup() {
        forgotPasswordViewModel =
            ForgotPasswordViewModel(usersUseCase)
    }
    @Test
    fun getUser() {
        runBlocking {
            //given
            val user = Users(birthday = "", password = "", identification = "", nameUser = "", phone = "", name = "", isAdmin = false, email = "a", imageUserUrl = "")
            coEvery { usersUseCase.searchUser("a") } returns Result.success(user)
            //when
            val result = forgotPasswordViewModel.getUser("a").getOrNull()
            //then
            assertEquals(user.email, result?.email)

            coVerify (exactly = 1){usersUseCase.searchUser("a")}
        }
    }
}