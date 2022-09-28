package com.example.andreymerkurev.crypto.presentation.feature.cryptolist.di

import com.example.andreymerkurev.crypto.presentation.di.annotation.FragmentScope
import com.example.andreymerkurev.crypto.presentation.feature.cryptolist.CryptoListFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [CryptoListModule::class])
interface CryptoListComponent {
    fun inject(cryptoListFragment: CryptoListFragment)
}