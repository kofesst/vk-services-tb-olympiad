package me.kofesst.vkservices.data.remote

import me.kofesst.vkservices.data.remote.responses.AllServicesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ServicesApi {
    @GET("/semi-final-data.json")
    suspend fun getAllServices(): Response<AllServicesResponse>
}