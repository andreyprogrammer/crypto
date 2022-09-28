package com.example.andreymerkurev.crypto.presentation.di.modules

import android.content.Context
import com.example.andreymerkurev.crypto.data.network.CryptoApi
import com.example.andreymerkurev.crypto.data.network.PicassoLoader
import com.example.andreymerkurev.crypto.data.repository.CryptoRepositoryImpl
import com.example.andreymerkurev.crypto.domain.interactors.CryptoInteractor
import com.example.andreymerkurev.crypto.domain.interactors.ICryptoInteractor
import com.example.andreymerkurev.crypto.domain.repository.ICryptoRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideContext() = context

    @Singleton
    @Provides
    fun provideCryptoRepository(
        cryptoApi: CryptoApi
    ): ICryptoRepository =
        CryptoRepositoryImpl(cryptoApi)

    @Singleton
    @Provides
    fun provideCryptoInteractor(cryptoRepository: ICryptoRepository): ICryptoInteractor =
        CryptoInteractor(cryptoRepository)

    @Singleton
    @Provides
    fun providePicasso(): PicassoLoader = PicassoLoader()
}