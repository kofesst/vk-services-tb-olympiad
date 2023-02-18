package me.kofesst.vkservices.domain.utils

sealed class RemoteResponse<T : Any>(
    val body: T?,
    val isSuccess: Boolean,
) {
    class Ok<T : Any>(body: T) : RemoteResponse<T>(
        body,
        isSuccess = true,
    )

    data class Error<T : Any>(val statusCode: Int) : RemoteResponse<T>(
        body = null,
        isSuccess = false
    )
}