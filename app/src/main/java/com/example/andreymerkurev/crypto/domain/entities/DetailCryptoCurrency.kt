package com.example.andreymerkurev.crypto.domain.entities

data class DetailCryptoCurrency(
    val id: String = "",
    val name: String = "",
    val image: CryptoImage = CryptoImage(""),
    val description: CryptoDescription = CryptoDescription(""),
    val categories: List<String> = listOf()
)