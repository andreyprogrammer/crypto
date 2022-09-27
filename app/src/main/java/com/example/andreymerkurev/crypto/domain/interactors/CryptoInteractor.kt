package com.example.andreymerkurev.crypto.domain.interactors

import com.example.andreymerkurev.crypto.domain.entities.Cryptocurrency
import com.example.andreymerkurev.crypto.domain.entities.DetailCryptocurrency
import com.example.andreymerkurev.crypto.domain.entities.RequestResult
import com.example.andreymerkurev.crypto.domain.repository.ICryptoRepository

interface ICryptoInteractor {
    suspend fun getAllCryptocurrencies(
        vsCurrency: String,
        perPage: Int,
        page: Int
    ): RequestResult<List<Cryptocurrency>>

    suspend fun getCryptocurrency(id: String): RequestResult<DetailCryptocurrency>
}

class CryptoInteractor(
    private val cryptoRepository: ICryptoRepository
) : ICryptoInteractor {
    override suspend fun getAllCryptocurrencies(
        vsCurrency: String,
        perPage: Int,
        page: Int
    ): RequestResult<List<Cryptocurrency>> =
        cryptoRepository.getAllCryptocurrencies(vsCurrency, perPage, page)

    override suspend fun getCryptocurrency(id: String): RequestResult<DetailCryptocurrency> =
        cryptoRepository.getCryptocurrency(id)
}