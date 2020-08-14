package com.nullit.test_task.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nullit.test_task.ui.api.dto.BestBookDto
import com.nullit.test_task.ui.api.dto.MainCarouselDtoItem
import com.nullit.test_task.ui.api.dto.SimilarBookDto
import com.nullit.test_task.ui.repo.BookRepository
import kotlinx.coroutines.launch

class BooksViewModel : ViewModel() {

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading

    private val _snackbarMessage = MutableLiveData<String>()
    val snackbar: LiveData<String>
        get() = _snackbarMessage

    // для детальной информации
    private val _bestSellerDetailedItem = MutableLiveData<BestBookDto>()
    val bestBoolDetails: LiveData<BestBookDto>
        get() = _bestSellerDetailedItem

    // для вертикального списка
    private val _bestBooks = MutableLiveData<List<BestBookDto>>()
    val bestBooks: LiveData<List<BestBookDto>>
        get() = _bestBooks

    // для главной карусели
    private val _carouselBooks = MutableLiveData<List<MainCarouselDtoItem>>()
    val carouselBooks: LiveData<List<MainCarouselDtoItem>>
        get() = _carouselBooks

    // для обычной карусели
    private val _similarBooks = MutableLiveData<List<SimilarBookDto>>()
    val similarsBooks: LiveData<List<SimilarBookDto>>
        get() = _similarBooks

    fun requestBestBooks() {
        viewModelScope.launch {
            _loading.value = true
            val result = BookRepository.requestBestBooks()
            if (result.isNotEmpty()) {
                _bestBooks.value = result
            } else {
                _snackbarMessage.value = "Не удалось получить данные"
            }
            _loading.value = false
        }
    }

    fun requestMainCarouselsItems() {
        viewModelScope.launch {
            _loading.value = true
            val result = BookRepository.requestMainCarouselBooks()
            if (result.isNotEmpty()) {
                _carouselBooks.value = result
            } else {
                _snackbarMessage.value = "Не удалось получить данные"
            }
            _loading.value = false
        }
    }

    fun requestSimilarBooks() {
        viewModelScope.launch {
            _loading.value = true
            val result = BookRepository.requestSimilarBooks()
            if (result.isNotEmpty()) {
                _similarBooks.value = result
            } else {
                _snackbarMessage.value = "Не удалось получить данные"
            }
            _loading.value = false
        }
    }

    fun onBestItemClick(bestBookDto: BestBookDto) {
        _bestSellerDetailedItem.value = bestBookDto
    }

    fun onMessageShown() {
        _snackbarMessage.value = null
    }

}