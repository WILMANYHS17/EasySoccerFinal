package com.example.easysoccer1.di.modules

import com.example.easysoccer1.data.database.DataBaseImpl
import com.example.easysoccer1.data.database.MapImpl
import com.example.easysoccer1.domain.DatabaseUserRepository
import com.example.easysoccer1.domain.MapRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {

    factory<DatabaseUserRepository> {
        DataBaseImpl(
            dataBase = get(),
            dataBaseStorage = get()
        )
    }
    factory<MapRepository>{
        MapImpl(
            apiService = get()
        )
    }
}