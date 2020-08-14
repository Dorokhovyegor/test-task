package com.nullit.test_task.ui.api

import com.nullit.test_task.ui.api.dto.BestBookDto
import com.nullit.test_task.ui.api.dto.MainCarouselDtoItem
import com.nullit.test_task.ui.api.dto.SimilarBookDto
import retrofit2.http.GET

interface ApiService {

    @GET("/stellardiver/ebookdata/carousel")
    suspend fun requestCarouselItems(): List<MainCarouselDtoItem>

    @GET("/stellardiver/ebookdata/best")
    suspend fun requestBestSellers(): List<BestBookDto>

    @GET("/stellardiver/ebookdata/similar")
    suspend fun requestSimilarBooks(): List<SimilarBookDto>

}