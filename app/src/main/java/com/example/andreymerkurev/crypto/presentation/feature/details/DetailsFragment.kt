package com.example.andreymerkurev.crypto.presentation.feature.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.andreymerkurev.crypto.R
import com.example.andreymerkurev.crypto.data.network.PicassoLoader
import com.example.andreymerkurev.crypto.databinding.FragmentDetailsBinding
import com.example.andreymerkurev.crypto.presentation.di.Injector
import com.example.andreymerkurev.crypto.presentation.di.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var picassoLoader: PicassoLoader

    val detailsViewModel: DetailsViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details, container, false
        )
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(binding.detailsToolbar)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Injector.getDetailsComponent().inject(this)
        val id = arguments?.getString("id") ?: ""
        lifecycleScope.launch {
            detailsViewModel.getCryptoDetails(id)
        }

        detailsViewModel.cryptoDetails.observeForever { it ->
            binding.detailsToolbarTitle.text = it.name
            binding.description.text = it.description.en
            binding.category.text = it.categories.joinToString(prefix = "", postfix = "")
            if (it.image.small != "")
                picassoLoader.loadImage(it.image.small, binding.coinImage)
        }

        detailsViewModel.isLoading.observeForever { isLoading ->
            if (isLoading) {
                binding.detailsProgress.visibility = View.VISIBLE
            } else {
                binding.detailsProgress.visibility = View.GONE
            }
        }

        detailsViewModel.isError.observeForever { isError ->
            if (isError) {
                binding.imageError.visibility = View.VISIBLE
                binding.textError.visibility = View.VISIBLE
                binding.btnTryAgainDetails.visibility = View.VISIBLE
            } else {
                binding.imageError.visibility = View.GONE
                binding.textError.visibility = View.GONE
                binding.btnTryAgainDetails.visibility = View.GONE
            }
        }

        binding.btnTryAgainDetails.setOnClickListener {
            detailsViewModel.getCryptoDetails(id)
        }

        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}