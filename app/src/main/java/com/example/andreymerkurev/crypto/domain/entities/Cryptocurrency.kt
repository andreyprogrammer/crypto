package com.example.andreymerkurev.crypto.domain.entities

import com.google.gson.annotations.SerializedName

data class Cryptocurrency(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    @SerializedName("current_price")
    val currentPrice: Int,
    @SerializedName("price_change_percentage_24h")
    val priceChange: Int
)