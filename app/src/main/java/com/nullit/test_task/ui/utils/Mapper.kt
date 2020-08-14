package com.nullit.test_task.ui.utils

import com.nullit.test_task.ui.api.dto.MainCarouselDtoItem
import com.nullit.test_task.ui.api.dto.SimilarBookDto

object Mapper {
    fun convertMainCarouselItemToSimilarItem(mainCarouselDtoItem: MainCarouselDtoItem): SimilarBookDto {
        return SimilarBookDto(mainCarouselDtoItem.id, mainCarouselDtoItem.imageUrl)
    }

    fun convertSimilarBooksToCarouselItem(item: SimilarBookDto): MainCarouselDtoItem {
        return MainCarouselDtoItem(item.id, item.image)
    }
}