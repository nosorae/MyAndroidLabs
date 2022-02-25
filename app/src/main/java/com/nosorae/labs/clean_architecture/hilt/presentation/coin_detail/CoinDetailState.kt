package com.nosorae.labs.clean_architecture.hilt.presentation.coin_detail

import com.nosorae.labs.clean_architecture.hilt.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String? = null
)
