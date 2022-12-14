package com.example.andreymerkurev.crypto.presentation.feature.cryptolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.andreymerkurev.crypto.R
import com.example.andreymerkurev.crypto.data.network.PicassoLoader
import com.example.andreymerkurev.crypto.databinding.FragmentCryptolistBinding
import com.example.andreymerkurev.crypto.presentation.di.Injector
import com.example.andreymerkurev.crypto.presentation.di.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject


class CryptoListFragment : Fragment() {
    private lateinit var binding: FragmentCryptolistBinding
    private lateinit var cryptoListAdapter: CryptoListAdapter

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
        cryptoListAdapter = CryptoListAdapter(
            requireContext(),
            onClick = {
                val bundle = bundleOf("id" to it)
                view.findNavController()
                    .navigate(R.id.action_cryptoListFragment_to_detailsFragment, bundle)
            },
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

        cryptoListViewModel.isLoading.observeForever { isLoading ->
            if (isLoading) {
                binding.cryptoListProgress.visibility = View.VISIBLE
            } else {
                binding.cryptoListProgress.visibility = View.GONE
            }
        }

        cryptoListViewModel.isError.observeForever { isError ->
            if (isError) {
                binding.imageError.visibility = View.VISIBLE
                binding.textError.visibility = View.VISIBLE
                binding.btnTryAgainList.visibility = View.VISIBLE
            } else {
                binding.imageError.visibility = View.GONE
                binding.textError.visibility = View.GONE
                binding.btnTryAgainList.visibility = View.GONE
            }
        }

        binding.btnTryAgainList.setOnClickListener {
            cryptoListViewModel.getCryptoCurrenciesList(selectCurrency(), PER_PAGE, PAGE)
        }

        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedId ->
            if (checkedId[0] == R.id.usdChip) {
                cryptoListViewModel.getCryptoCurrenciesList("usd", PER_PAGE, PAGE)
            } else {
                cryptoListViewModel.getCryptoCurrenciesList("eur", PER_PAGE, PAGE)
            }
        }
    }

    private fun selectCurrency(): String {
        var curr = ""
        if (binding.usdChip.isChecked)
            curr = "usd"
        if (binding.eurChip.isChecked)
            curr = "eur"
        return curr
    }
}