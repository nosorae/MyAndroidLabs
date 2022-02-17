package com.nosorae.labs.di_test.hilt.presentation.coin_list

import com.nosorae.labs.di_test.hilt.domain.model.Coin

sealed class CoinListState {
    object UnInitialized: CoinListState()
    object Loading: CoinListState()

    data class Success(
        val coins: List<Coin>
    ): CoinListState()

    data class Error(
        val message: String = ""
    ): CoinListState()

}