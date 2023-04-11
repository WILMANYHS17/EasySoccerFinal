package com.example.easysoccer1.di.modules

import com.example.easysoccer1.data.database.DataBaseImpl
import com.example.easysoccer1.domain.DatabaseUserRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {

    factory<DatabaseUserRepository> {
        DataBaseImpl(
            dataBase = get(),
        )
    }
}