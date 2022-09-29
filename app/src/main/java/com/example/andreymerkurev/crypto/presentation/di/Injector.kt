package com.example.andreymerkurev.crypto.presentation.di

import android.content.Context
import com.example.andreymerkurev.crypto.presentation.di.component.AppComponent
import com.example.andreymerkurev.crypto.presentation.di.component.DaggerAppComponent
import com.example.andreymerkurev.crypto.presentation.di.modules.AppModule
import com.example.andreymerkurev.crypto.presentation.feature.cryptolist.di.CryptoListComponent
import com.example.andreymerkurev.crypto.presentation.feature.details.di.DetailsComponent

object Injector {
    private lateinit var appComponent: AppComponent
    private var cryptoListComponent: CryptoListComponent? = null
    private var detailsComponent: DetailsComponent? = null

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

    fun getDetailsComponent(): DetailsComponent {
        if (detailsComponent == null) {
            detailsComponent = appComponent.detailsComponent
        }
        return detailsComponent!!
    }
}