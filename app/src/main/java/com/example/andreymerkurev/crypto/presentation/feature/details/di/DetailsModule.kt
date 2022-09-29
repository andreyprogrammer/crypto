package com.example.andreymerkurev.crypto.presentation.feature.details.di

import androidx.lifecycle.ViewModel
import com.example.andreymerkurev.crypto.presentation.di.annotation.ViewModelKey
import com.example.andreymerkurev.crypto.presentation.feature.details.DetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface DetailsModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel
}