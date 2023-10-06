package com.wyhs.easysoccer.di.modules

import com.wyhs.easysoccer.data.database.MapImpl
import com.wyhs.easysoccer.domain.DatabaseUserRepository
import com.wyhs.easysoccer.domain.MapRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {

    factory<DatabaseUserRepository> {
        com.wyhs.easysoccer.data.database.DataBaseImpl(
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