package com.nosorae.labs.clean_architecture.hilt.presentation.coin_list

import com.nosorae.labs.clean_architecture.hilt.domain.model.Coin

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