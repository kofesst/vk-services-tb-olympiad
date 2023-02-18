package me.kofesst.vkservices.data

import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import me.kofesst.vkservices.data.remote.ServicesApi
import me.kofesst.vkservices.data.repositories.ServicesRepositoryImpl
import me.kofesst.vkservices.domain.utils.RemoteResponse
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServicesRepositoryTests {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://mobile-olympiad-trajectory.hb.bizmrg.com")
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
        .create(ServicesApi::class.java)

    private val repository = ServicesRepositoryImpl(retrofit)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Check if getting all services via repository works as expected`() = runTest {
        val response = repository.getAll()
        assert(response is RemoteResponse.Ok) { "Response is not successful" }
        val services = response.body
        assert(services != null) { "Response body is null" }
        assert(services!!.isNotEmpty()) { "Services list is empty" }
        services.forEach { appService ->
            assert(appService.name.isNotBlank()) { "Service name is blank: $appService" }
            assert(appService.description.isNotBlank()) { "Service description is blank: $appService" }
            assert(appService.iconUrl.isNotBlank()) { "Service icon url is blank: $appService" }
            assert(appService.serviceUrl.isNotBlank()) { "Service url is blank: $appService" }
        }
    }
}