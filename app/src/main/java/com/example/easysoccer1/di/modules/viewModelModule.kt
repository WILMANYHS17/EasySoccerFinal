package com.example.easysoccer1.di.modules


import com.example.easysoccer1.ui.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {


    viewModel{
        JoinSessionViewModel(
            usersUseCase = get()

        )
    }
    viewModel{
        RegisterUserViewModel(
            usersUseCase = get()
        )
    }
    viewModel{
        ForgotPasswordViewModel(
            usersUseCase = get()
        )
    }

    viewModel{
        HomeAdminViewModel(
            sportCenterUseCase = get()
        )
    }

    viewModel{
        SelectSportCenterViewModel(
            sportCenterUseCase = get()
        )
    }
    viewModel{
        RegisterSportCenterViewModel(
            sportCenterUseCase = get()
        )
    }
}