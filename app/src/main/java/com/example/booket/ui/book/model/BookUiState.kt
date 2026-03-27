package com.example.booket.ui.book.model

import com.example.booket.data.remote.model.BookResponse

// 화면 상태를 제한된 타입으로 안전하게 표현
sealed class BookUiState {
    object Idle : BookUiState() // 초기 상태
    object Loading : BookUiState() // 데이터 로딩 중 상태
    data class Success(val data: BookResponse) : BookUiState() // 데이터 요청 성공 및 결과 포함 상태
    data class Error(val message: String) : BookUiState() // 데이터 요청 실패 및 에러 메시지 포함 상태
}