package com.example.andreymerkurev.crypto.domain.entities

data class DetailCryptoCurrency(
    val id: String,
    val name: String,
    val image: CryptoImage,
    val description: CryptoDescription,
    val categories: List<String>
)