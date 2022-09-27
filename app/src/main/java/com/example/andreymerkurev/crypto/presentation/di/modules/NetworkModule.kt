package com.example.andreymerkurev.crypto.presentation.di.modules

import com.example.andreymerkurev.crypto.data.network.CryptoApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideCryptoApi(retrofit: Retrofit) = retrofit.create(CryptoApi::class.java)

    companion object {
        private const val BASE_URL = "https://api.coingecko.com/api/v3/"
    }
}