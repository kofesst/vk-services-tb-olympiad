package me.kofesst.vkservices.domain.repositories

import me.kofesst.vkservices.domain.models.AppService
import me.kofesst.vkservices.domain.utils.RemoteResponse

interface ServicesRepository {
    suspend fun getAll(): RemoteResponse<List<AppService>>
}