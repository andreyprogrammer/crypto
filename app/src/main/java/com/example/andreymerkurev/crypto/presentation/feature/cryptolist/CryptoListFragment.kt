package com.example.andreymerkurev.crypto.presentation.feature.cryptolist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.andreymerkurev.crypto.R
import com.example.andreymerkurev.crypto.data.network.PicassoLoader
import com.example.andreymerkurev.crypto.databinding.FragmentCryptolistBinding
import com.example.andreymerkurev.crypto.presentation.di.Injector
import com.example.andreymerkurev.crypto.presentation.di.ViewModelFactory
import com.example.andreymerkurev.crypto.presentation.feature.ItemClickListener
import kotlinx.coroutines.launch
import javax.inject.Inject


class CryptoListFragment : Fragment() {
    private lateinit var binding: FragmentCryptolistBinding
    private lateinit var cryptoListAdapter: CryptoListAdapter
    private lateinit var itemClickListener: ItemClickListener

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var picassoLoader: PicassoLoader

    val cryptoListViewModel: CryptoListViewModel by viewModels {
        viewModelFactory
    }

    companion object {
        private const val PER_PAGE = 30
        private const val PAGE = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_cryptolist, container, false
        )
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(binding.cryptoListToolbar)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Injector.getCryptoListComponent().inject(this)
        Log.d("key01", "start")
        cryptoListAdapter = CryptoListAdapter(
            onClick = { itemClickListener.onItemClick(it) },
            picassoLoader
        )
        binding.cryptoListRecyclerView.adapter = cryptoListAdapter

        lifecycleScope.launch {
            cryptoListViewModel.getCryptoCurrenciesList(selectCurrency(), PER_PAGE, PAGE)
        }

        cryptoListViewModel.listCryptoCurrencies.observeForever {
            cryptoListAdapter.submitList(cryptoListViewModel.listCryptoCurrencies.value)
            (binding.cryptoListRecyclerView.adapter as CryptoListAdapter).notifyDataSetChanged()
        }

        binding.chipGroup.setOnCheckedStateChangeListener { _, checkedId ->
            Log.d("key01", "checkedId=$checkedId")
            if (checkedId == binding.usdChip) {
                cryptoListViewModel.getCryptoCurrenciesList("usd", PER_PAGE, PAGE)
            } else
                cryptoListViewModel.getCryptoCurrenciesList("eur", PER_PAGE, PAGE)
        }
    }

    private fun selectCurrency(): String {
        var curr = ""
        if (binding.usdChip.isChecked)
            curr = "usd"
        if (binding.eurChip.isChecked)
            curr =  "eur"
        return curr
    }
}