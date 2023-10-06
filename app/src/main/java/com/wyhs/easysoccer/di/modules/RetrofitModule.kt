package com.wyhs.easysoccer.di.modules

import com.wyhs.easysoccer.di.network.NetworkClient
import com.wyhs.easysoccer.domain.ApiService
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

