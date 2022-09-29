package com.example.andreymerkurev.crypto.presentation.feature.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andreymerkurev.crypto.domain.entities.CryptoCurrency
import com.example.andreymerkurev.crypto.domain.entities.DetailCryptoCurrency
import com.example.andreymerkurev.crypto.domain.entities.RequestResult
import com.example.andreymerkurev.crypto.domain.interactors.ICryptoInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val cryptoInteractor: ICryptoInteractor
) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>().apply {
        value = false
    }
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<Boolean>().apply {
        value = false
    }
    val isError: LiveData<Boolean> = _isError

    private val _cryptoDetails = MutableLiveData<DetailCryptoCurrency>().apply {
        value = DetailCryptoCurrency()
    }
    val cryptoDetails: LiveData<DetailCryptoCurrency> = _cryptoDetails

    fun getCryptoDetails(
        id: String
    ) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _isError.value = false
                _cryptoDetails.value = DetailCryptoCurrency()
                val response = cryptoInteractor.getCryptocurrency(id)
                when (response) {
                    is RequestResult.Success -> {
                        _cryptoDetails.value = response.data
                        _isLoading.value = false
                    }
                    is RequestResult.Error -> {
                        _isLoading.value = false
                        _isError.value = true
                    }
                }
            } catch (e: Exception) {
                _isLoading.value = false
                _isError.value = true
            }
        }
    }
}