package com.example.easysoccer1.data.database

import com.example.easysoccer1.data.models.ForgotPassword
import com.example.easysoccer1.data.models.Users
import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.domain.DatabaseUserRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class DataBaseImpl(
    private val dataBase: FirebaseFirestore
) : DatabaseUserRepository {

    override fun createUser(users: Users) {
        dataBase.collection("Users").document(users.email).set(
            hashMapOf(
                "name" to users.name,
                "phone" to users.phone,
                "email" to users.email,
                "nameUser" to users.nameUser,
                "password" to users.password,
                "birthday" to users.birthday,
                "isAdmin" to (users.isAdmin),
                "identification" to (users.identification),
                "nit" to (users.nit)
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
        dataBase.collection("Users").document(forgotPassword.email).set(
            hashMapOf(
                "password" to forgotPassword.password
            )
        )
    }

    override fun createSportCenter(sportCenter: SportCenter) {
        dataBase.collection("Users").document(sportCenter.emailAdmin).collection("SportCenter")
            .document(sportCenter.emailAdmin).set(
                hashMapOf(
                    "nameSportCenter" to sportCenter.name,
                    "address" to sportCenter.address,
                    "description" to sportCenter.description,
                    "nit" to sportCenter.nit,
                    "5vs5" to sportCenter.price5vs5,
                    "8vs8" to sportCenter.price8vs8
                )
            )
    }

    override suspend fun getSportCenter(sportCenter: SportCenter): Result<SportCenter> {

        val snapshot =
            dataBase.collection("Users").document(sportCenter.emailAdmin).collection("SportCenter")
                .document(sportCenter.emailAdmin).get().await()
        var nameSportCenter = snapshot.get("nameSportCenter") as? String
        var address = snapshot.get("nameSportCenter") as? String
        var description = snapshot.get("description") as? String
        var nit = snapshot.get("nit") as? String
        var price5vs5 = snapshot.get("5vs5") as? String
        var price8vs8 = snapshot.get("8vs8") as? String
        sportCenter.name = nameSportCenter.toString()
        sportCenter.address = address.toString()
        sportCenter.description = description.toString()
        sportCenter.nit = nit.toString()
        sportCenter.price5vs5 = price5vs5.toString()
        sportCenter.price8vs8 = price8vs8.toString()
        return Result.success(sportCenter)
    }

    override suspend fun searchUser(email: String): Result<Users> {
        val snapshot = dataBase.collection("Users").document(email).get().await()
        val users = snapshot.toObject(Users::class.java)
        return if (users != null) {
            Result.success(users)
        } else {
            Result.failure(Exception("Algo pasó"))
        }

    }

    fun getListSportCenter() {
/*
        val collectionRef = dataBase.collection("Users")

        collectionRef.whereEqualTo("isAdmin", true).get().addOnSuccessListener { documents ->
            if (documents != null) {
                val tuLista = ArrayList<SportCenter>()
                for (document in documents) {
                    val tuObjeto = document.toObject(TuClase::class.java)
                    tuLista.add(tuObjeto)
                }
                // Aquí puedes agregar tu lista a un RecyclerView usando un adaptador personalizado
            } else {
                Log.d("Consulta", "No such document")
            }
        }.addOnFailureListener { exception ->
            Log.d("Consulta", "get failed with ", exception)
        }

 */
    }


}