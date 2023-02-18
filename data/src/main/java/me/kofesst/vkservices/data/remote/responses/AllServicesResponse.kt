package me.kofesst.vkservices.data.remote.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.kofesst.vkservices.data.remote.dto.AppServiceDto

@Serializable
data class AllServicesResponse(
    @SerialName("items")
    val items: List<AppServiceDto> = emptyList(),
)
