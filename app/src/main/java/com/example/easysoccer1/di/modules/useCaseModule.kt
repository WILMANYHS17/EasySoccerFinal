package com.example.easysoccer1.di.modules

import com.example.easysoccer1.domain.*
import org.koin.core.module.Module
import org.koin.dsl.module

val useCaseModule: Module = module {

    factory{
        UsersUseCase(
            databaseUserRepository = get()
        )
    }
    factory {
        SportCenterUseCase(
            databaseUserRepository = get()
        )
    }
    factory{
        GoalsUseCase(
            databaseUserRepository = get()
        )
    }
    factory{
        ReserveUseCase(
            databaseUserRepository = get()
        )
    }
    factory{
        CommentsUseCase(
            databaseUserRepository = get()
        )
    }
    factory {
        MapUseCase(
            mapRepository = get()
        )
    }


}