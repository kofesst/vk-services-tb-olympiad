package me.kofesst.vkservices.data.repositories

import me.kofesst.vkservices.data.remote.ServicesApi
import me.kofesst.vkservices.data.utils.handle
import me.kofesst.vkservices.domain.models.AppService
import me.kofesst.vkservices.domain.repositories.ServicesRepository
import me.kofesst.vkservices.domain.utils.RemoteResponse

class ServicesRepositoryImpl(
    private val servicesApi: ServicesApi,
) : ServicesRepository {
    override suspend fun getAll(): RemoteResponse<List<AppService>> {
        val apiResponse = servicesApi.getAllServices()
        return apiResponse.handle { allServicesResponse ->
            allServicesResponse.items.map { item ->
                item.toModel()
            }
        }
    }
}