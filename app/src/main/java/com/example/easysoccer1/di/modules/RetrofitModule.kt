package com.example.easysoccer1.di.modules

import com.example.easysoccer1.ui.interfaces.ApiService
import com.jhonatanrojas.searchapp.di.network.NetworkClient
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by JHONATAN ROJAS on 8/10/2021.
 */

val retrofitModule: Module = module {

    single(named("retrofitApi")) {
        NetworkClient().getRetrofitInstance("https://api.openrouteservice.org/")
    }

    single { get<Retrofit>(named("retrofitApi")).create(ApiService::class.java) }
}

