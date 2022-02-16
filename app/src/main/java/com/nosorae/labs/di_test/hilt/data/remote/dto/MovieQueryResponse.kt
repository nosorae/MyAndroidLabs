package com.nosorae.labs.di_test.hilt.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieQueryResponse(
    @SerializedName("actor")
    val actor: String?,
    @SerializedName("director")
    val director: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("pubDate")
    val pubDate: String?,
    @SerializedName("subtitle")
    var subtitle: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("userRating")
    val userRating: String?
)