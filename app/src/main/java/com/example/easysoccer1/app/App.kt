package com.example.easysoccer1.app

import android.app.Application
import com.example.easysoccer1.di.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        //Todo vamos a injectar Koin injecta en tiempo de ejecucion
        initKoin()
    }

    private fun initKoin(){
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules( listOf(
                firebaseDb,
                retrofitModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            ))
        }
    }

}