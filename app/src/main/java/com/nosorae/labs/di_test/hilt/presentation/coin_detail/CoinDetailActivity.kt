package com.nosorae.labs.di_test.hilt.presentation.coin_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.nosorae.labs.R
import com.nosorae.labs.databinding.ActivityCoinDetailBinding
import com.nosorae.labs.di_test.hilt.common.Constant.PARAM_COIN_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailActivity : AppCompatActivity() {
    private val viewModel: CoinDetailViewModel by viewModels()
    lateinit var binding: ActivityCoinDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeState()
    }
    private fun observeState() {
        viewModel.state.observe(this) { state ->
            binding.tvCoinDetail.text = state.coin.toString()
        }
    }
}