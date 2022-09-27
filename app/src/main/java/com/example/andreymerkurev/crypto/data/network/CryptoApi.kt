package com.example.andreymerkurev.crypto.data.network

import com.example.andreymerkurev.crypto.domain.entities.Cryptocurrency
import com.example.andreymerkurev.crypto.domain.entities.DetailCryptocurrency
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoApi {
    @GET("coins/markets")
    suspend fun getAllCryptocurrencies(
        @Query("vs_currency") vsCurrency: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
    ): Response<List<Cryptocurrency>>

    @GET("coins/{id}")
    suspend fun getCryptocurrency(
        @Path("id") id: String
    ): Response<DetailCryptocurrency>
}