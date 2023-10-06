package com.wyhs.easysoccer.domain

import com.wyhs.easysoccer.data.models.Users
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class UsersUseCaseTest {
    private var databaseUserRepository: DatabaseUserRepository = mockk(relaxed = true)
    private lateinit var usersUseCase: UsersUseCase

    @Before
    fun setup() {
        usersUseCase = UsersUseCase(databaseUserRepository)
    }

    @Test
    fun searchUser() {
        runBlocking {
            //given
            val user = Users(
                birthday = "",
                password = "",
                identification = "",
                nameUser = "",
                phone = "",
                name = "",
                isAdmin = false,
                email = "a",
                imageUserUrl = ""
            )
            coEvery { databaseUserRepository.searchUser("a") } returns Result.success(user)
            //when
            val result = usersUseCase.searchUser("a").getOrNull()
            //then
            assertEquals(user.email, result?.email)

            coVerify(exactly = 1) { databaseUserRepository.searchUser("a") }
        }

    }

    @Test
    fun getImage() {
        runBlocking {
            //given
            coEvery { databaseUserRepository.getImageUser("a") } returns Result.success("a")
            //when
            val result = usersUseCase.getImageUser("a").getOrNull()
            //then
            assertEquals("a", result)
            coVerify { databaseUserRepository.getImageUser("a") }
        }
    }

    @Test
    fun getUsers() {
        runBlocking {
            //given
            val user = Users(
                birthday = "",
                password = "",
                identification = "",
                nameUser = "",
                phone = "",
                name = "",
                isAdmin = false,
                email = "a",
                imageUserUrl = ""
            )
            coEvery { databaseUserRepository.getUserComplete("a") } returns Result.success(user)
            //when
            val result = usersUseCase.getUser("a").getOrNull()
            //then
            assertEquals(user.email, result?.email)

            coVerify(exactly = 1) { databaseUserRepository.getUserComplete("a") }
        }
    }

}