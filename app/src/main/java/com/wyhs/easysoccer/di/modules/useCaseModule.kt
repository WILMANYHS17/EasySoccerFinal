package com.wyhs.easysoccer.di.modules

import com.wyhs.easysoccer.domain.*
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