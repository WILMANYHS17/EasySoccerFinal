package com.example.easysoccer1.di.modules

import com.google.firebase.firestore.FirebaseFirestore
import org.koin.core.module.Module
import org.koin.dsl.module

val firebaseDb: Module = module {

    single {
        FirebaseFirestore.getInstance()
    }
}