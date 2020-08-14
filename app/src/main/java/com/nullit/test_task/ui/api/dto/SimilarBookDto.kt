package com.nullit.test_task.ui.api.dto

import com.google.gson.annotations.SerializedName

data class SimilarBookDto(
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String
)