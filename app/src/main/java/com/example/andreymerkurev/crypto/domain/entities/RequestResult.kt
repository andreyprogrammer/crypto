package com.example.andreymerkurev.crypto.domain.entities

sealed class RequestResult<out T : Any> {
    class Success<out T : Any>(val data: T) : RequestResult<T>()
    class Error<out T : Any> : RequestResult<T>()
}