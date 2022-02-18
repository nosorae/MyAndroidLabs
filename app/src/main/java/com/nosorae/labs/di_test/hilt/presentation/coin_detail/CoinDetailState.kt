package com.nosorae.labs.di_test.hilt.presentation.coin_detail

import com.nosorae.labs.di_test.hilt.domain.model.Coin
import com.nosorae.labs.di_test.hilt.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String? = null
)
