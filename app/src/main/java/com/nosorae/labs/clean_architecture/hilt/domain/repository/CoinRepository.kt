package com.nosorae.labs.clean_architecture.hilt.domain.repository

import com.nosorae.labs.clean_architecture.hilt.data.remote.dto.CoinDetailDto
import com.nosorae.labs.clean_architecture.hilt.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoin(coinId: String): CoinDetailDto
}