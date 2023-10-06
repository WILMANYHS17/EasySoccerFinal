package com.wyhs.easysoccer.ui.viewmodel

import com.wyhs.easysoccer.data.models.Users
import com.wyhs.easysoccer.domain.UsersUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*



class RegisterUserViewModelTest {
    private val usersUseCase: UsersUseCase = mockk(relaxed = true)
    private lateinit var registerUserViewModel: RegisterUserViewModel

    @Before
    fun setup() {
        registerUserViewModel =
            RegisterUserViewModel(usersUseCase)
    }
    @Test
    fun getUser() {
        runBlocking {
            //given
            val user = Users(birthday = "", password = "", identification = "", nameUser = "", phone = "", name = "", isAdmin = false, email = "a", imageUserUrl = "")
            coEvery { usersUseCase.searchUser("a") } returns Result.success(user)
            //when
            val result = registerUserViewModel.getUser("a").getOrNull()
            //then
            assertEquals(user.email, result?.email)

            coVerify (exactly = 1){usersUseCase.searchUser("a")}
        }
    }

    @Test
    fun getImageUser() {
        runBlocking {
            //given
            coEvery { usersUseCase.getImageUser("a") } returns Result.success("a")
            //when
            val result = registerUserViewModel.getImageUser("a").getOrNull()
            //then
            assertEquals("a", result)
            coVerify { usersUseCase.getImageUser("a") }
        }
    }
}