package me.kofesst.vkservices.data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import me.kofesst.vkservices.domain.models.AppService

@Serializable
data class AppServiceDto(
    @SerializedName("name")
    val name: String = "",

    @SerializedName("description")
    val description: String = "",

    @SerializedName("icon_url")
    val iconUrl: String = "",

    @SerializedName("service_url")
    val serviceUrl: String,
) {
    fun toModel() = AppService(name, description, iconUrl, serviceUrl)
}
