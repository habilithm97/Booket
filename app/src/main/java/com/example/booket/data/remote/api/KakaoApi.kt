package com.example.booket.data.remote.api

import com.example.booket.data.remote.model.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoApi {
    @GET("v3/search/book") // GET 방식으로 카카오 책 검색 API 호출
    suspend fun searchBooks(
        @Query("query") query: String // 검색어를 쿼리 파라미터로 전달
    ): BookResponse // 서버 응답을 BookResponse 객체로 반환
}

/**
 * Retrofit 인터페이스
  - Retrofit은 인터페이스에 정의된 함수를 통해 HTTP 요청을 만듦
   -> 함수는 서버로 요청을 보내고, 응답을 코틀린 객체로 변환
 */