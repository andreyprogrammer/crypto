package com.example.andreymerkurev.crypto.presentation.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.andreymerkurev.crypto.presentation.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {
    @Binds
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}