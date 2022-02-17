package com.nosorae.labs.di_test.hilt.presentation.coin_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nosorae.labs.databinding.RvItemCoinListBinding
import com.nosorae.labs.di_test.hilt.domain.model.Coin

class CoinListAdapter: RecyclerView.Adapter<CoinListAdapter.ViewHolder>() {

    private var coins = mutableListOf<Coin>()

    inner class ViewHolder(private val binding: RvItemCoinListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Coin) = with(binding) {
            binding.tvCoinInfo.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
        = ViewHolder(RvItemCoinListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bindData(coins[position])
    }

    override fun getItemCount(): Int = coins.size

    fun addList(list: List<Coin>) {
        coins.addAll(list)
    }
}