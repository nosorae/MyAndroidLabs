package com.nosorae.labs.di_test.hilt.di

import com.nosorae.labs.di_test.hilt.common.Constant.BASE_URL
import com.nosorae.labs.di_test.hilt.data.remote.CoinPaprikaApi
import com.nosorae.labs.di_test.hilt.data.repository.CoinRepositoryImpl
import com.nosorae.labs.di_test.hilt.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // 모듈안에 있는 의존성의 스코프
object AppModule  {

    @Provides
    @Singleton // 스코프동안 싱글톤 객체로 지정
    fun provideParprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // 시리얼 디시리얼라이즈 데이터
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository { // 위에서 힐트가 CoinPaprikaApi 어떻게 만들지 알려줬기 때문에 인자로 넣어준다.
       return CoinRepositoryImpl(api)
    }

}