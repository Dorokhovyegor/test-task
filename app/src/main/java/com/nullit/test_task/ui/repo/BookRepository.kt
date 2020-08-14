package com.nullit.test_task.ui.repo

import com.nullit.test_task.ui.api.RetrofitBuilder
import com.nullit.test_task.ui.api.dto.BestBookDto
import com.nullit.test_task.ui.api.dto.MainCarouselDtoItem
import com.nullit.test_task.ui.api.dto.SimilarBookDto

object BookRepository {

    suspend fun requestBestBooks(): List<BestBookDto> {
        // можно было бы промапить, но данные слишком простые
       return RetrofitBuilder.apiService.requestBestSellers()
    }

    suspend fun requestMainCarouselBooks(): List<MainCarouselDtoItem> {
        // можно было бы промапить, но данные слишком простые
        return RetrofitBuilder.apiService.requestCarouselItems()
    }

    suspend fun requestSimilarBooks(): List<SimilarBookDto> {
        // можно было бы промапить, но данные слишком простые
        return RetrofitBuilder.apiService.requestSimilarBooks()
    }

}