package com.nosorae.labs.di_test.hilt.di

import com.nosorae.labs.BuildConfig
import com.nosorae.labs.di_test.hilt.common.Constant.BASE_URL
import com.nosorae.labs.di_test.hilt.data.remote.CoinPaprikaApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


//@Module
//@InstallIn(SingletonComponent::class)
//object RetrofitModule {
//
//    @Singleton
//    @Provides
//    fun provideRetrofitInstance(): Retrofit =
//        Retrofit
//            .Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(buildOkHttpClient())
//            .build()
//
//
//
//    @Singleton
//    @Provides
//    fun provideMovieApi(
//        retrofit: Retrofit
//    ): CoinPaprikaApi =
//        retrofit.create(CoinPaprikaApi::class.java)
//
//
//    private fun buildOkHttpClient(): OkHttpClient {
//        val interceptor = HttpLoggingInterceptor()
//
//        if (BuildConfig.DEBUG) {
//            interceptor.level = HttpLoggingInterceptor.Level.BODY
//        } else {
//            interceptor.level = HttpLoggingInterceptor.Level.BODY // TODO NONE 으로 다시 바꿀 것
//        }
//
//        return OkHttpClient.Builder()
//            .connectTimeout(5, TimeUnit.SECONDS)
//            .connectTimeout(5, TimeUnit.SECONDS)
//            .addInterceptor(interceptor)
//            .build()
//    }
//}
