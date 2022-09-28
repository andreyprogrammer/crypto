package com.example.andreymerkurev.crypto.domain.interactors

import com.example.andreymerkurev.crypto.domain.entities.CryptoCurrency
import com.example.andreymerkurev.crypto.domain.entities.DetailCryptoCurrency
import com.example.andreymerkurev.crypto.domain.entities.RequestResult
import com.example.andreymerkurev.crypto.domain.repository.ICryptoRepository
import javax.inject.Inject

interface ICryptoInteractor {
    suspend fun getAllCryptoCurrencies(
        vsCurrency: String,
        perPage: Int,
        page: Int
    ): RequestResult<List<CryptoCurrency>>

    suspend fun getCryptocurrency(id: String): RequestResult<DetailCryptoCurrency>
}

class CryptoInteractor @Inject constructor(
    private val cryptoRepository: ICryptoRepository
) : ICryptoInteractor {
    override suspend fun getAllCryptoCurrencies(
        vsCurrency: String,
        perPage: Int,
        page: Int
    ): RequestResult<List<CryptoCurrency>> =
        cryptoRepository.getAllCryptocurrencies(vsCurrency, perPage, page)

    override suspend fun getCryptocurrency(id: String): RequestResult<DetailCryptoCurrency> =
        cryptoRepository.getCryptocurrency(id)
}