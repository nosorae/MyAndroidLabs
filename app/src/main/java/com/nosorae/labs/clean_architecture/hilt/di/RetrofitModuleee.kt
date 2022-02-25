package com.nosorae.labs.clean_architecture.hilt.di


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
