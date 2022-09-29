package com.example.andreymerkurev.crypto.presentation.feature.details.di

import com.example.andreymerkurev.crypto.presentation.di.annotation.FragmentScope
import com.example.andreymerkurev.crypto.presentation.feature.details.DetailsFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [DetailsModule::class])
interface DetailsComponent {
    fun inject(detailsFragment: DetailsFragment)
}