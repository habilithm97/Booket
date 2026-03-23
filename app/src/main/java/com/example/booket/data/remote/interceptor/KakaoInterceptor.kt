package com.example.booket.data.remote.interceptor

import com.example.booket.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class KakaoInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder() // 기존 요청 수정
            .addHeader("Authorization", "KakaoAK ${BuildConfig.KAKAO_API_KEY}") // 카카오 인증 헤더 추가
            .build()
        return chain.proceed(request) // 요청 실행 후 응답 반환
    }
}