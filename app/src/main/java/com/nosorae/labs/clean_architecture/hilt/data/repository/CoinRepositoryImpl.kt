package com.nosorae.labs.clean_architecture.hilt.data.repository

import com.nosorae.labs.clean_architecture.hilt.data.remote.CoinPaprikaApi
import com.nosorae.labs.clean_architecture.hilt.data.remote.dto.CoinDetailDto
import com.nosorae.labs.clean_architecture.hilt.data.remote.dto.CoinDto
import com.nosorae.labs.clean_architecture.hilt.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
): CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoin(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}