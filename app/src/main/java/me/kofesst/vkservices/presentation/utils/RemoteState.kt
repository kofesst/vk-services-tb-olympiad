package me.kofesst.vkservices.presentation.utils

import me.kofesst.vkservices.domain.utils.RemoteResponse

sealed interface RemoteState<T : Any> {
    class Default<T : Any> : RemoteState<T>
    class Fetching<T : Any> : RemoteState<T>
    data class Loaded<T : Any>(val response: RemoteResponse<T>) : RemoteState<T>
    data class Failed<T : Any>(val statusCode: Int) : RemoteState<T>
}