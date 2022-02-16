package com.nosorae.labs.di_test.hilt.data.remote.api

import android.provider.SyncStateContract
import com.nosorae.labs.di_test.hilt.common.Constant.SEARCH_MOVIE_URL
import com.nosorae.labs.di_test.hilt.data.remote.dto.MovieQueryResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieApi {
    @Headers("X-Naver-Client-Id: Y2NAMEqbtiS3rTm5XPPj", "X-Naver-Client-Secret: I02dkZj3c4")
    @GET(SEARCH_MOVIE_URL)
    suspend fun searchMovies(
        @Query("query") keyword: String,
        @Query("display") display: Int,
    ): MovieQueryResponse
}