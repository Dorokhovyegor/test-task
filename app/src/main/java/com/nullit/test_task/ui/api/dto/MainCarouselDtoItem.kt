package com.nullit.test_task.ui.api.dto

import com.google.gson.annotations.SerializedName

data class MainCarouselDtoItem(
    @SerializedName("id") val id: Int,
    @SerializedName("image") val imageUrl: String
)