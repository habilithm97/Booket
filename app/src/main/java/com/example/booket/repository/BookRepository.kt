package com.example.booket.repository

import com.example.booket.data.remote.api.KakaoApi
import com.example.booket.data.remote.model.BookResponse

// Repository 패턴 : UI 로직과 데이터 로직을 분리 (데이터 소스 추상화)
class BookRepository(private val api: KakaoApi) {
    // 책 검색 API 결과를 Result로 반환
    suspend fun searchBooks(query: String): Result<BookResponse> {
        return try {
            Result.success(api.searchBooks(query))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}