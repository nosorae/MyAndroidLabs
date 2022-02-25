package com.nosorae.labs.clean_architecture.hilt.presentation.coin_detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.nosorae.labs.databinding.ActivityCoinDetailBinding
import com.nosorae.labs.clean_architecture.hilt.common.Constants.PARAM_COIN_ID
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