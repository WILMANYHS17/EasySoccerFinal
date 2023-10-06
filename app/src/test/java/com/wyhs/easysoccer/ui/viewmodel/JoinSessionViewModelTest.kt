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



class JoinSessionViewModelTest {
    private val usersUseCase: UsersUseCase = mockk(relaxed = true)
    private lateinit var joinSessionViewModel: JoinSessionViewModel

    @Before
    fun setup() {
        joinSessionViewModel =
            JoinSessionViewModel(usersUseCase)
    }

    @Test
    fun searchUsers1() {
        runBlocking {
            //given
            val user = Users(birthday = "", password = "", identification = "", nameUser = "", phone = "", name = "", isAdmin = false, email = "a", imageUserUrl = "")
            coEvery { usersUseCase.searchUser("a") } returns Result.success(user)
            //when
            val result = joinSessionViewModel.searchUsers1("a").getOrNull()
            //then
            assertEquals(user.email, result?.email)

            coVerify (exactly = 1){usersUseCase.searchUser("a")}
        }
    }
}