package com.nosorae.labs.clean_architecture.hilt.presentation.coin_list.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nosorae.labs.databinding.RvItemCoinListBinding
import com.nosorae.labs.clean_architecture.hilt.domain.model.Coin

class CoinListAdapter(
    private val onClickCoin: (String) -> Unit,
) : RecyclerView.Adapter<CoinListAdapter.ViewHolder>() {

    private var coins = mutableListOf<Coin>()

    inner class ViewHolder(private val binding: RvItemCoinListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Coin) = with(binding) {
            binding.tvCoinInfo.text = data.name
        }

        fun bindView(data: Coin) {
            binding.root.setOnClickListener {
                onClickCoin(data.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(RvItemCoinListBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(coins[position])
        holder.bindView(coins[position])
    }

    override fun getItemCount(): Int = coins.size

    fun addList(list: List<Coin>) {
        Log.d("asdf", "$list")
        coins.addAll(list)
        notifyDataSetChanged()
    }

}