package com.maplescouter.domain.model

/*
    라이브러리 의존성 문제를 해결하기 위함
    data 계층에서 사용하는 Response<T>는 Retrofit 네트워크 통신에 종속적이므로
    도메인 계층에서 사용할 수 없음
    또한 안드로이드 UI 상태 관리를 위해 커스텀하여 생성
 */

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val message: String, val throwable: Throwable? = null) : ApiResult<Nothing>()
    object Loading : ApiResult<Nothing>()
}
