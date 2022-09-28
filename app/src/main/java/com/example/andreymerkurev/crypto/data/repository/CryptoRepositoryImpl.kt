package com.example.andreymerkurev.crypto.data.repository

import com.example.andreymerkurev.crypto.data.network.CryptoApi
import com.example.andreymerkurev.crypto.domain.entities.CryptoCurrency
import com.example.andreymerkurev.crypto.domain.entities.DetailCryptoCurrency
import com.example.andreymerkurev.crypto.domain.entities.RequestResult
import com.example.andreymerkurev.crypto.domain.repository.ICryptoRepository
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val cryptoApi: CryptoApi
) : ICryptoRepository {
    override suspend fun getAllCryptocurrencies(
        vsCurrency: String,
        perPage: Int,
        page: Int
    ): RequestResult<List<CryptoCurrency>> {
        return try {
            val response = cryptoApi.getAllCryptocurrencies(vsCurrency, perPage, page)
            if (response.isSuccessful) {
                val data = response.body()
                RequestResult.Success(data!!)
            } else {
                RequestResult.Error()
            }
        } catch (e: Exception) {
            RequestResult.Error()
        }
    }

    override suspend fun getCryptocurrency(id: String): RequestResult<DetailCryptoCurrency> {
        return try {
            val response = cryptoApi.getCryptocurrency(id)
            if (response.isSuccessful) {
                val data = response.body()
                RequestResult.Success(data!!)
            } else {
                RequestResult.Error()
            }
        } catch (e: Exception) {
            RequestResult.Error()
        }
    }
}