package com.example.andreymerkurev.crypto.domain.repository

import com.example.andreymerkurev.crypto.domain.entities.CryptoCurrency
import com.example.andreymerkurev.crypto.domain.entities.DetailCryptoCurrency
import com.example.andreymerkurev.crypto.domain.entities.RequestResult

interface ICryptoRepository {
    suspend fun getAllCryptocurrencies(
        vsCurrency: String,
        perPage: Int,
        page: Int
    ): RequestResult<List<CryptoCurrency>>

    suspend fun getCryptocurrency(id: String): RequestResult<DetailCryptoCurrency>
}