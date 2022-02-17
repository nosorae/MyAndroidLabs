package com.nosorae.labs.di_test.hilt.domain.repository

import com.nosorae.labs.di_test.hilt.data.remote.dto.CoinDetailDto
import com.nosorae.labs.di_test.hilt.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoin(coinId: String): CoinDetailDto
}