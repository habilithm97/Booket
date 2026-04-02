package com.example.booket.ui.book.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.booket.repository.BookRepository

// Repository를 전달해 ViewModel을 생성하는 Factory 클래스
@Suppress("UNCHECKED_CAST")
class BookViewModelFactory(private val repository: BookRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = // 요청된 ViewModel 생성
        when {
            // BookViewModel이면 생성 후 반환
            modelClass.isAssignableFrom(BookViewModel::class.java) -> BookViewModel(repository) as T
            else -> throw IllegalArgumentException("알 수 없는 타입")
        }
}