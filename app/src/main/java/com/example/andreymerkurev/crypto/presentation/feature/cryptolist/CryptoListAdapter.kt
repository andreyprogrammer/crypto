package com.example.andreymerkurev.crypto.presentation.feature.cryptolist

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.andreymerkurev.crypto.R
import com.example.andreymerkurev.crypto.data.network.PicassoLoader
import com.example.andreymerkurev.crypto.databinding.ItemCryptoBinding
import com.example.andreymerkurev.crypto.domain.entities.CryptoCurrency

class CryptoListAdapter(
    private val context: Context,
    private val onClick: (String) -> Unit,
    private val picassoLoader: PicassoLoader
) : ListAdapter<CryptoCurrency, CryptoListAdapter.CryptoListViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoListViewHolder {
        return CryptoListViewHolder(
            context,
            ItemCryptoBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick,
            picassoLoader
        )
    }

    override fun onBindViewHolder(holder: CryptoListViewHolder, position: Int) {
        val crypto = getItem(position)
        holder.bind(crypto)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CryptoCurrency>() {
        override fun areItemsTheSame(old: CryptoCurrency, aNew: CryptoCurrency): Boolean {
            return old === aNew
        }

        override fun areContentsTheSame(old: CryptoCurrency, aNew: CryptoCurrency): Boolean {
            return old.id == aNew.id
        }
    }

    class CryptoListViewHolder(
        private val context: Context,
        private val binding: ItemCryptoBinding,
        private val onClick: (String) -> Unit,
        private val picassoLoader: PicassoLoader
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cryptoCurrency: CryptoCurrency) {
            itemView.setOnClickListener {
                onClick(cryptoCurrency.id)
            }
            if (cryptoCurrency.priceChange >= 0)
                binding.cryptoItemPriceChange.setTextColor(
                    ResourcesCompat.getColor(
                        context.resources,
                        R.color.item_price_change_plus,
                        null
                    )
                )
            else
                binding.cryptoItemPriceChange.setTextColor(
                    ResourcesCompat.getColor(
                        context.resources,
                        R.color.item_price_change_minus,
                        null
                    )
                )
            binding.cryptoItemName.text = cryptoCurrency.name
            binding.cryptoItemSymbol.text = cryptoCurrency.symbol
            binding.cryptoItemPrice.text = cryptoCurrency.currentPrice.toString()
            binding.cryptoItemPriceChange.text = "${cryptoCurrency.priceChange}%"
            picassoLoader.loadImage(cryptoCurrency.image, binding.cryptoItemImage)
        }
    }
}