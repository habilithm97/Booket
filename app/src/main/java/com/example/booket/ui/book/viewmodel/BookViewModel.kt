package com.example.booket.ui.book.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booket.repository.BookRepository
import com.example.booket.ui.book.model.BookUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BookRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<BookUiState>(BookUiState.Idle)
    val uiState: StateFlow<BookUiState> = _uiState

    private var searchJob: Job? = null // 이전 검색 작업 취소용

    fun searchBooks(query: String) {
        val trimmedQuery = query.trim()

        if (trimmedQuery.isEmpty()) return

        searchJob?.cancel() // 이전 검색 취소

        searchJob = viewModelScope.launch {
            _uiState.value = BookUiState.Loading

            repository.searchBooks(trimmedQuery)
                .onSuccess { books -> _uiState.value = BookUiState.Success(books) }
                .onFailure { e -> _uiState.value = BookUiState.Error(e.message ?: "알 수 없는 오류") }
        }
    }
}