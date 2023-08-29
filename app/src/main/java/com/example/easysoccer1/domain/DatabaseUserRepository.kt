package com.example.easysoccer1.domain

import android.net.Uri
import com.example.easysoccer1.data.models.*

interface DatabaseUserRepository {


    // Users Funtions
    fun createUser(users: Users)
    suspend fun getUser(email: String, password: String): Result<Boolean>
    fun changePassword(forgotPassword: Users)
    suspend fun searchUser(email: String): Result<Users>
    suspend fun getUserComplete(emailUser: String): Result<Users>
    fun setImageUser(uriImageUser: Uri, emailUser: String)
    suspend fun getImageUser(email: String): Result<String?>

    //SportCenter Funtions
    fun createSportCenter(sportCenter: SportCenter)
    suspend fun getSportCenter(nit: String, email: String): Result<SportCenter>
    suspend fun getListSportCenter(email: String?): Result<List<SportCenter>>
    fun setImageSportCenter(nit: String, uriImageSportCenter: Uri)
    suspend fun getImageSportCenter(nit: String): Result<String>
    fun setListImageSportCenter(uriList: MutableList<Uri>, nit: String)
    suspend fun getListImageSportCenter(nit: String): Result<List<String>>

    //Goals Funtions
    fun setGoals(goals: Goals, emailAdmin: String?, nit: String?)
    suspend fun getListGoals(emailAdmin: String?, nit: String?): Result<List<Goals>>
    fun deleteGoal(emailAdmin: String?, nit: String?, number: String)

    //SportCenter Users Funtions

    suspend fun getSportCenterUser(nit: String?): Result<SportCenter>
    suspend fun getListSportsCenterUsers(date: String, hour: String, optionsFinal: String): Result<List<SportCenter>>

    //Reserve Users Funtions
    fun setReserve(reserve: Reserve, emailUser: String?)
    suspend fun getListReserveUser(emailUser: String?): Result<List<Reserve>>
    suspend fun getGoal(nit: String, size: String): Result<Goals>
    suspend fun updateGoal(updateGoal: Goals, number: String, nit: String)
    suspend fun getListReserveAdmin(nameSportCenter:String): Result<List<Reserve>>

    //Comments Users Funtions
    fun setComment(comment: Comments)
    suspend fun getListComments(nameSportCenter: String): Result<List<Comments>>




}