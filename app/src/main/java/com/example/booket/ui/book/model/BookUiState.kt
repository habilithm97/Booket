package com.example.booket.ui.book.model

import com.example.booket.data.remote.model.Book

// 화면 상태를 제한된 타입으로 안전하게 관리
sealed class BookUiState {
    object Idle : BookUiState() // 초기 상태
    object Loading : BookUiState() // 로딩 중 상태
    data class Success(val data: List<Book>) : BookUiState() // 성공 상태 (데이터 포함)
    data class Error(val message: String) : BookUiState() // 실패 상태 (에러 메시지 포함)
}