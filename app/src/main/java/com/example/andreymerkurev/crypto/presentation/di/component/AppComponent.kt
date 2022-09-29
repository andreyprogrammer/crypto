package com.example.andreymerkurev.crypto.presentation.di.component

import android.content.Context
import com.example.andreymerkurev.crypto.presentation.di.modules.AppModule
import com.example.andreymerkurev.crypto.presentation.di.modules.NetworkModule
import com.example.andreymerkurev.crypto.presentation.di.modules.ViewModelFactoryModule
import com.example.andreymerkurev.crypto.presentation.feature.cryptolist.di.CryptoListComponent
import com.example.andreymerkurev.crypto.presentation.feature.details.di.DetailsComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ViewModelFactoryModule::class])
interface AppComponent {
    val context: Context
    val cryptoListComponent: CryptoListComponent
    val detailsComponent: DetailsComponent
}