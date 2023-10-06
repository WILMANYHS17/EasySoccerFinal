package com.wyhs.easysoccer.di.modules


import com.wyhs.easysoccer.ui.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {


    viewModel {
        JoinSessionViewModel(
            usersUseCase = get()

        )
    }
    viewModel {
        RegisterUserViewModel(
            usersUseCase = get()
        )
    }
    viewModel {
        ForgotPasswordViewModel(
            usersUseCase = get()
        )
    }

    viewModel {
        HomeAdminViewModel(
            sportCenterUseCase = get(),
            commentsUseCase = get()
        )
    }

    viewModel {
        SelectSportCenterViewModel(
            sportCenterUseCase = get()
        )
    }
    viewModel {
        RegisterSportCenterViewModel(
            sportCenterUseCase = get()
        )
    }
    viewModel {
        GoalsViewModel(
            goalsUseCase = get()
        )
    }
    viewModel {
        HomeUserViewModel(
            usersUseCase = get()
        )
    }
    viewModel {
        DescriptionSportCenterViewModel(
            sportCenterUseCase = get(),
            usersUseCase = get(),
            commentsUseCase = get()
        )
    }
    viewModel {
        HeaderProfileUserViewModel(
            usersUseCase = get()
        )
    }
    viewModel {
        ReserveUserViewModel(
            reserveUseCase = get(),
            sportCenterUseCase = get(),
            goalsUseCase = get()
        )
    }
    viewModel {
        ReserveUserFragmentViewModel(
            reserveUseCase = get()
        )
    }
    viewModel {
        ReserveAdminViewModel(
            reserveUseCase = get()
        )
    }
    viewModel {
        MapViewModel(
            mapUseCase = get()
        )
    }
    viewModel{
        StatsViewModel(
            reserveUseCase = get()
        )
    }
}