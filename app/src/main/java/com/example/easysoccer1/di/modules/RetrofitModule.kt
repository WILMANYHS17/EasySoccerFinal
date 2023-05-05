package com.example.easysoccer1.di.modules

import com.example.easysoccer1.di.network.NetworkClient
import com.example.easysoccer1.domain.ApiService
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit



val retrofitModule: Module = module {

    single(named("retrofitApi")) {
        NetworkClient().getRetrofitInstance("https://api.openrouteservice.org/")
    }

    single { get<Retrofit>(named("retrofitApi")).create(ApiService::class.java) }
}

