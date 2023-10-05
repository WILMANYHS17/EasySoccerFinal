package com.example.easysoccer1.ui.viewmodel

import com.example.easysoccer1.data.models.Comments
import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.domain.CommentsUseCase
import com.example.easysoccer1.domain.SportCenterUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*



class HomeAdminViewModelTest {
    private val sportCenterUseCase: SportCenterUseCase = mockk(relaxed = true)
    private val commentsUseCase: CommentsUseCase= mockk(relaxed = true)
    private lateinit var homeAdminViewModel: HomeAdminViewModel
    @Before
    fun setup() {
        homeAdminViewModel =
            HomeAdminViewModel(sportCenterUseCase, commentsUseCase)
    }

    @Test
    fun getSportCenter() {
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
            coEvery { sportCenterUseCase.getSportCenter("a","a") } returns Result.success(sportCenter)
            //when
            val result = homeAdminViewModel.getSportCenter("a", "a").getOrNull()
            //then
            assertEquals(sportCenter.nameSportCenter, result?.nameSportCenter)

            coVerify(exactly = 1) { sportCenterUseCase.getSportCenter("a", "a") }
        }
    }

    @Test
    fun getListComments() {
        runBlocking {
            //given
            val commentList = listOf(
                Comments(
                    emailUser = "",
                    nameUser = "",
                    nameSportCenter = "",
                    comment = "",
                    id = "1",
                ),
                Comments(
                    emailUser = "",
                    nameUser = "",
                    nameSportCenter = "",
                    comment = "",
                    id = "2",
                )
            )

            coEvery { commentsUseCase.getListComments("a") } returns Result.success(
                commentList
            )
            //when
            val result = homeAdminViewModel.getListComments("a").getOrNull()
            //then
            assertEquals(commentList.last(), result?.last())
            coVerify { commentsUseCase.getListComments("a") }
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
            val result = homeAdminViewModel.getListImageSportCenter("a").getOrNull()
            //then
            assertEquals(listImage.last(), result?.last())
            coVerify { sportCenterUseCase.getListImageSportCenter("a") }
        }
    }
}