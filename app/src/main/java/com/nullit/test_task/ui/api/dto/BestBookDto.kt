package com.nullit.test_task.ui.api.dto

import com.google.gson.annotations.SerializedName

data class BestBookDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("author") val author: String,
    @SerializedName("price") val price: Float,
    @SerializedName("image") val image: String,
    @SerializedName("rate") val rate: Rate
)

data class Rate(
    @SerializedName("score") val score: Float,
    @SerializedName("amount") val amount: Int
)