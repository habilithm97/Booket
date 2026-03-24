package com.example.booket.data.remote.client

import com.example.booket.data.remote.api.KakaoApi
import com.example.booket.data.remote.interceptor.KakaoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://dapi.kakao.com/"

    // 네트워크 요청을 처리하는 OkHttp 클라이언트 생성
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(KakaoInterceptor()) // 모든 요청에 API 키를 자동 추가하는 인터셉터
            .build()
    }
    // Retrofit 객체 생성
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()) // JSON 데이터를 객체로 변환하기 위한 Gson 컨버터
            .build()
    }
    // KakaoApi 인터페이스를 실제 구현체로 변환
    val kakaoApi: KakaoApi by lazy {
        retrofit.create(KakaoApi::class.java)
    }
}

/** 싱글톤 구현 -> 네트워크 객체 재사용 및 인터셉터를 통한 공통 헤더 처리 **/