package com.example.easysoccer1.data.database

import com.example.easysoccer1.data.models.Goals
import com.example.easysoccer1.data.models.Reserve
import com.example.easysoccer1.data.models.Users
import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.domain.DatabaseUserRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await

class DataBaseImpl(
    private val dataBase: FirebaseFirestore
) : DatabaseUserRepository {

    // Users Funtions
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
            )
        )
    }

    override suspend fun getUser(email: String, password: String): Result<Boolean> {
        val snapshot = dataBase.collection("Users").document(email).get().await()
        val passwordDb = snapshot.get("password")
        return Result.success(password == passwordDb)

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

    override fun changePassword(forgotPassword: Users) {
        dataBase.collection("Users").document(forgotPassword.email).set(
            hashMapOf(
                "name" to forgotPassword.name,
                "phone" to forgotPassword.phone,
                "email" to forgotPassword.email,
                "nameUser" to forgotPassword.nameUser,
                "password" to forgotPassword.password,
                "birthday" to forgotPassword.birthday,
                "isAdmin" to (forgotPassword.isAdmin),
                "identification" to (forgotPassword.identification)
            )
        )
    }

    //SportCenter Funtions

    override fun createSportCenter(sportCenter: SportCenter) {
        dataBase.collection("Users").document(sportCenter.emailAdmin).collection("SportCenter")
            .document(sportCenter.nit).set(
                hashMapOf(
                    "nameSportCenter" to sportCenter.nameSportCenter,
                    "address" to sportCenter.address,
                    "description" to sportCenter.description,
                    "nit" to sportCenter.nit,
                    "price5vs5" to sportCenter.price5vs5,
                    "price8vs8" to sportCenter.price8vs8
                )
            )
    }

    override suspend fun getSportCenter(nit: String, email: String): Result<SportCenter> {

        val snapshot =
            dataBase.collection("Users").document(email).collection("SportCenter").document(nit)
                .get().await()
        val sportCenter = snapshot.toObject(SportCenter::class.java)

        return if (sportCenter != null) {
            Result.success(sportCenter)
        } else {
            Result.failure(Exception("Algo salió mal"))
        }
    }


    override suspend fun getListSportCenter(email: String?): Result<List<SportCenter>> {
        val list = mutableListOf<SportCenter>()
        val snapshot =
            dataBase.collection("Users").document(email.toString()).collection("SportCenter").get()
                .await()

        for (document in snapshot.documents) {
            val sportCenter = document.toObject(SportCenter::class.java)
            sportCenter?.let {
                list.add(sportCenter)
            }

        }

        return Result.success(list)
    }

    //Goals Funtions

    override fun setGoals(goals: Goals, emailAdmin: String?, nit: String?) {
        dataBase.collection("Users").document(emailAdmin.toString()).collection("SportCenter")
            .document(nit.toString()).collection("Goals").document(goals.number).set(
                hashMapOf(
                    "number" to goals.number,
                    "size" to goals.size,
                    "price" to goals.price,
                    "available" to goals.available,
                    "hour" to goals.hour,
                    "date" to goals.date
                )
            )
    }

    override suspend fun getListGoals(emailAdmin: String?, nit: String?): Result<List<Goals>> {
        val list = mutableListOf<Goals>()
        val snapshot =
            dataBase.collection("Users").document(emailAdmin.toString()).collection("SportCenter")
                .document(nit.toString()).collection("Goals").get().await()

        for (document in snapshot.documents) {
            val goals = document.toObject(Goals::class.java)
            goals?.let {
                list.add(goals)
            }

        }

        return Result.success(list)
    }

    override fun deleteGoal(emailAdmin: String?, nit: String?, number: String) {
        dataBase.collection("Users").document(emailAdmin.toString()).collection("SportCenter")
            .document(nit.toString()).collection("Goals").document(number).delete()
    }

    //SportCenter Users Funtions

    override suspend fun getSportCenterUser(nit: String?): Result<SportCenter> {
        val snapshot = dataBase.collection("Users").whereEqualTo("isAdmin", true).get().await()
        for (document in snapshot.documents) {
            val collectionSportCenter =
                document.reference.collection("SportCenter").whereEqualTo("nit", nit).get().await()
            if (!collectionSportCenter.isEmpty) {
                val sportCenterDocument = collectionSportCenter.documents[0]
                val sportCenter = sportCenterDocument.toObject(SportCenter::class.java)
                return Result.success(sportCenter) as Result<SportCenter>
            }
        }
        return Result.failure(Exception("No se encontró ningún SportCenter con el NIT proporcionado"))
    }

    override suspend fun getListSportsCenterUsers(): Result<List<SportCenter>> {
        val list = mutableListOf<SportCenter>()
        val snapshot =
            dataBase.collection("Users").whereEqualTo("isAdmin", true).get().await()

        for (document in snapshot.documents) {
            val collectionSportCenter = document.reference.collection("SportCenter")
            val sportCenterSnapshot = collectionSportCenter.get().await()
            for (sportCenter in sportCenterSnapshot.documents) {
                val sportCenterSearch = sportCenter.toObject(SportCenter::class.java)
                sportCenterSearch?.let {
                    list.add(sportCenterSearch)
                }
            }
        }

        return Result.success(list)
    }

    //Reserve Users Funtions
    override fun setReserve(reserve: Reserve, emailUser: String?) {

        dataBase.collection("Users").document(emailUser.toString()).collection("Reservations")
            .document(reserve.numberReserve).set(
                hashMapOf(
                    "numberReserve" to reserve.numberReserve,
                    "nameSportCenter" to reserve.nameSportCenter,
                    "nameReserveBy" to reserve.nameReserveBy,
                    "date" to reserve.date,
                    "hour" to reserve.hour,
                    "price" to reserve.price,
                    "paidOrNot" to reserve.paidOrNot,
                    "address" to reserve.address,
                    "numberPlayers" to reserve.numberPlayers
                )
            )
    }

    override suspend fun getListReserveUser(emailUser: String?): Result<List<Reserve>> {
        val list = mutableListOf<Reserve>()
        val snapshot =
            dataBase.collection("Users").document(emailUser.toString()).collection("Reservations")
                .get().await()

        for (document in snapshot.documents) {
            val reserveSearch = document.toObject(Reserve::class.java)
            reserveSearch?.let {
                list.add(reserveSearch)

            }
        }

        return Result.success(list)
    }

}