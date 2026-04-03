package com.example.booket.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

private var toast: Toast? = null

// 문자열 메시지를 Toast로 표시하는 확장 함수
fun Context.showToast(message: String) {
    toast?.cancel() // 이전 toast 취소
    // applicationContext -> 안전한 Context 사용 (메모리 누수 방지)
    toast = Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT)
    toast?.show()
}

// 문자열 리소스 id를 Toast로 표시하는 확장 함수
fun Context.showToast(@StringRes resId: Int) {
    showToast(getString(resId))
}