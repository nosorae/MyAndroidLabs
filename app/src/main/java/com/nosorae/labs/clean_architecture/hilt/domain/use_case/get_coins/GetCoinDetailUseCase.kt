package com.nosorae.labs.clean_architecture.hilt.domain.use_case.get_coins

import com.nosorae.labs.clean_architecture.hilt.common.Resource
import com.nosorae.labs.clean_architecture.hilt.data.remote.dto.toCoinDetail
import com.nosorae.labs.clean_architecture.hilt.domain.model.CoinDetail
import com.nosorae.labs.clean_architecture.hilt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    // override invoke operator function
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coinDetail = repository.getCoin(coinId).toCoinDetail()
            emit(Resource.Success(data = coinDetail))
        } catch (e: HttpException) {
            // 레스폰스 코드가 2로 시작하지 않을 때
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            // 인터넷 연결 문제든 서버가 오프라인이든 서버와 통신이 안될때?
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))

        }
    }
}