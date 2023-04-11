package com.example.easysoccer1.di.modules

import com.example.easysoccer1.domain.SetUsersUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val useCaseModule: Module = module {
    factory {
        SetUsersUseCase(
            databaseUserRepository = get()
        )
    }
}