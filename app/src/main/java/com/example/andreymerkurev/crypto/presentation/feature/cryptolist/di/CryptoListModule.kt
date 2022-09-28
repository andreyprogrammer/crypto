package com.example.andreymerkurev.crypto.presentation.feature.cryptolist.di

import androidx.lifecycle.ViewModel
import com.example.andreymerkurev.crypto.presentation.di.annotation.ViewModelKey
import com.example.andreymerkurev.crypto.presentation.feature.cryptolist.CryptoListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CryptoListModule {
    @Binds
    @IntoMap
    @ViewModelKey(CryptoListViewModel::class)
    fun bindCryptoListViewModel(viewModel: CryptoListViewModel): ViewModel
}