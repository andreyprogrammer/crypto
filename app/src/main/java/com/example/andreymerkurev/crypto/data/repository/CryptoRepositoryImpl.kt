package com.example.andreymerkurev.crypto.data.repository

import com.example.andreymerkurev.crypto.data.network.CryptoApi
import com.example.andreymerkurev.crypto.domain.entities.Cryptocurrency
import com.example.andreymerkurev.crypto.domain.entities.DetailCryptocurrency
import com.example.andreymerkurev.crypto.domain.entities.RequestResult
import com.example.andreymerkurev.crypto.domain.repository.ICryptoRepository

class CryptoRepositoryImpl(
    private val cryptoApi: CryptoApi
) : ICryptoRepository {
    override suspend fun getAllCryptocurrencies(
        vsCurrency: String,
        perPage: Int,
        page: Int
    ): RequestResult<List<Cryptocurrency>> {
        return try {
            val response = cryptoApi.getAllCryptocurrencies(vsCurrency, perPage, page)
            if (response.isSuccessful) {
                val data = response.body()
                RequestResult.Success(data)
            } else {
                RequestResult.Error()
            }
        } catch (e: Exception) {
            RequestResult.Error()
        }
    }

    override suspend fun getCryptocurrency(id: String): RequestResult<DetailCryptocurrency> {
        return try {
            val response = cryptoApi.getCryptocurrency(id)
            if (response.isSuccessful) {
                val data = response.body()
                RequestResult.Success(data)
            } else {
                RequestResult.Error()
            }
        } catch (e: Exception) {
            RequestResult.Error()
        }
    }
}