package com.nosorae.labs.di_test.hilt.presentation.coin_detail

import android.content.Intent
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
        initTextView()
    }
    private fun observeState() {
        viewModel.state.observe(this) { state ->
            binding.tvCoinDetail.text = state.coin.toString()
        }
    }
    private fun initTextView() {
        binding.tvCoinDetail.setOnClickListener {
            Intent().putExtra(PARAM_COIN_ID, binding.tvCoinDetail.text.toString()).also { i ->
                setResult(RESULT_OK, i)
                finish()
            }
        }
    }
}