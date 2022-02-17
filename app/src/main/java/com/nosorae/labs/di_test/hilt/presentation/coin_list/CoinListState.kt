package com.nosorae.labs.di_test.hilt.presentation.coin_list

import com.nosorae.labs.di_test.hilt.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String =""
)
