package com.example.andreymerkurev.crypto.domain.repository

import com.example.andreymerkurev.crypto.domain.entities.Cryptocurrency
import com.example.andreymerkurev.crypto.domain.entities.DetailCryptocurrency
import com.example.andreymerkurev.crypto.domain.entities.RequestResult

interface ICryptoRepository {
    suspend fun getAllCryptocurrencies(
        vsCurrency: String,
        perPage: Int,
        page: Int
    ): RequestResult<List<Cryptocurrency>>

    suspend fun getCryptocurrency(id: String): RequestResult<DetailCryptocurrency>
}