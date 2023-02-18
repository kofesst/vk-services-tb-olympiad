package me.kofesst.vkservices.data.utils

import me.kofesst.vkservices.domain.utils.RemoteResponse
import retrofit2.Response

internal inline fun <reified T : Any, reified R : Any> Response<T>.handle(
    crossinline mapper: (T) -> R,
): RemoteResponse<R> {
    val data: T? = body()
    if (!isSuccessful || data == null) {
        return RemoteResponse.Error(this.code())
    }
    val mapped = mapper(data)
    return RemoteResponse.Ok(mapped)
}