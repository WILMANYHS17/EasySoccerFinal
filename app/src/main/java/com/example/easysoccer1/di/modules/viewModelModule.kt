package com.example.easysoccer1.di.modules

import com.example.easysoccer1.ui.viewmodel.RegisterUserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {

    viewModel {
        RegisterUserViewModel(
            setUsersUseCase = get(),
        )
    }
}