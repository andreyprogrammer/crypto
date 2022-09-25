package com.example.andreymerkurev.crypto.presentation.feature.cryptolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.andreymerkurev.crypto.R
import com.example.andreymerkurev.crypto.databinding.FragmentCryptolistBinding

class CryptoListFragment : Fragment() {
    private lateinit var binding: FragmentCryptolistBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_cryptolist, container, false
        )
        if (activity is AppCompatActivity){
            (activity as AppCompatActivity).setSupportActionBar(binding.cryptoListToolbar)
        }

        return binding.root
    }
}