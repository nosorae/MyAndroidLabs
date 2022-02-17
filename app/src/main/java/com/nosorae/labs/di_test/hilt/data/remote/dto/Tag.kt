package com.nosorae.labs.di_test.hilt.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("coin_counter")
    val coinCounter: Int,
    @SerializedName("ico_counter")
    val icoCounter: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)