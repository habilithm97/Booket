package com.example.booket.ui.book.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booket.repository.BookRepository
import com.example.booket.ui.book.model.BookUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BookRepository) : ViewModel() {

    // UI 상태 관리 StateFlow
    private val _uiState = MutableStateFlow<BookUiState>(BookUiState.Idle)
    val uiState: StateFlow<BookUiState> = _uiState

    // UI에서 호출
    fun searchBooks(query: String) {
        if (query.isBlank()) return

        viewModelScope.launch {
            // 로딩 상태로 변경 -> UI에서 로딩 표시 가능
            _uiState.value = BookUiState.Loading

            // API 호출
            repository.searchBooks(query)
                // 성공 -> 데이터를 포함한 상태로 변경
                .onSuccess { response ->
                    _uiState.value = BookUiState.Success(response)
                }
                // 실패 -> 에러 메시지를 포함한 상태로 변경
                .onFailure { e ->
                    _uiState.value = BookUiState.Error(e.message ?: "알 수 없는 오류")
                }
        }
    }
}