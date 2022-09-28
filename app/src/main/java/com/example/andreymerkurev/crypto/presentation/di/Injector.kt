package com.example.andreymerkurev.crypto.presentation.di

import android.content.Context
import com.example.andreymerkurev.crypto.presentation.di.component.AppComponent
import com.example.andreymerkurev.crypto.presentation.di.component.DaggerAppComponent
import com.example.andreymerkurev.crypto.presentation.di.modules.AppModule
import com.example.andreymerkurev.crypto.presentation.feature.cryptolist.di.CryptoListComponent

object Injector {
    private lateinit var appComponent: AppComponent
    private var cryptoListComponent: CryptoListComponent? = null

    fun createAppComponent(context: Context) {
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(context))
            .build()
    }

    fun getCryptoListComponent(): CryptoListComponent {
        if (cryptoListComponent == null) {
            cryptoListComponent = appComponent.cryptoListComponent
        }
        return cryptoListComponent!!
    }
}