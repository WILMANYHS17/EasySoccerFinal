package com.example.easysoccer1.data.database

import com.example.easysoccer1.data.models.ForgotPassword
import com.example.easysoccer1.data.models.JoinSessionUsers
import com.example.easysoccer1.data.models.RegisterUsers
import com.example.easysoccer1.domain.DatabaseUserRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class DataBaseImpl(
    private val dataBase: FirebaseFirestore
) : DatabaseUserRepository {

    override fun createUser(registerUsers: RegisterUsers) {
        dataBase.collection("Users").document(registerUsers.email).set(
            hashMapOf(
                "name" to registerUsers.name,
                "phone" to registerUsers.phone,
                "email" to registerUsers.email,
                "nameUser" to registerUsers.nameUser,
                "password" to registerUsers.password,
                "birthday" to registerUsers.birthday,
                "isAdmin" to (registerUsers.isAdmin == "Admin")
            )
        )
    }


    override suspend fun getUser(email: String, password: String): Result<Boolean> {

        val snapshot = dataBase.collection("Users").document(email).get().await()
        val passwordDb = snapshot.get("password")
        return Result.success(password == passwordDb)

    }

    override suspend fun getIsAdmin(email: String, isAdmin: Boolean): Result<Boolean> {

            val snapshot = dataBase.collection("Users").document(email).get().await()
            val isAdminFromDb = snapshot.get("isAdmin") as? Boolean
            return Result.success(isAdmin == isAdminFromDb)
        }

    override fun changePassword(forgotPassword: ForgotPassword) {
        //dataBase.collection("Users").document(forgotPassword.email).set(
        //    hashMapOf()
        // )
    }




}