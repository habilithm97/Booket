package com.example.booket.repository

import com.example.booket.data.remote.api.KakaoApi
import com.example.booket.data.remote.model.Book

// Repository 패턴 : UI 로직과 데이터 로직을 분리 (데이터 소스 추상화)
class BookRepository(private val api: KakaoApi) {
    // 검색어로 API 호출 후 결과를 Result로 반환
    suspend fun searchBooks(query: String): Result<List<Book>> =
        runCatching { api.searchBooks(query).documents }
}