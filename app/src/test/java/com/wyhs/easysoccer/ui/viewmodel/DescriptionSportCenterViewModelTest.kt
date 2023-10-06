package com.wyhs.easysoccer.ui.viewmodel

import com.wyhs.easysoccer.data.models.SportCenter
import com.wyhs.easysoccer.data.models.Users
import com.wyhs.easysoccer.domain.CommentsUseCase
import com.wyhs.easysoccer.domain.SportCenterUseCase
import com.wyhs.easysoccer.domain.UsersUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*


class DescriptionSportCenterViewModelTest {
    private var sportCenterUseCase: SportCenterUseCase = mockk(relaxed = true)
    private var usersUseCase: UsersUseCase = mockk(relaxed = true)
    private var commentsUseCase: CommentsUseCase = mockk(relaxed = true)
    private lateinit var descriptionSportCenterViewModel: DescriptionSportCenterViewModel

    @Before
    fun setup() {
        descriptionSportCenterViewModel =
            DescriptionSportCenterViewModel(sportCenterUseCase, usersUseCase, commentsUseCase)
    }

    @Test
    fun getSportCenterUser() {
        runBlocking {
            //given
            val sportCenter = SportCenter(
                nameSportCenter = "Gym A",
                address = "",
                nit = "",
                price5vs5 = "",
                price8vs8 = "",
                description = "",
                emailAdmin = "",
                imageSportCenterUrl = "",
                locationSportCenter = ""
            )
            coEvery { sportCenterUseCase.getSportCenterUser("a") } returns Result.success(sportCenter)
            //when
            val result = descriptionSportCenterViewModel.getSportCenterUser("a").getOrNull()
            //then
            assertEquals(sportCenter.nameSportCenter, result?.nameSportCenter)

            coVerify(exactly = 1) { sportCenterUseCase.getSportCenterUser("a") }
        }
    }

    @Test
    fun getUser() {
        runBlocking {
            //given
            val user = Users(birthday = "", password = "", identification = "", nameUser = "", phone = "", name = "", isAdmin = false, email = "a", imageUserUrl = "")
            coEvery { usersUseCase.getUser("a") } returns Result.success(user)
            //when
            val result = descriptionSportCenterViewModel.getUser("a").getOrNull()
            //then
            assertEquals(user.email, result?.email)

            coVerify (exactly = 1){usersUseCase.getUser("a")}
        }
    }

    @Test
    fun getListImageSportCenter() {
        runBlocking {
            //given
            val listImage = listOf("a", "a", "w")
            coEvery { sportCenterUseCase.getListImageSportCenter("a") } returns Result.success(
                listImage
            )
            //when
            val result = descriptionSportCenterViewModel.getListImageSportCenter("a").getOrNull()
            //then
            assertEquals(listImage.last(), result?.last())
            coVerify { sportCenterUseCase.getListImageSportCenter("a") }
        }
    }
}