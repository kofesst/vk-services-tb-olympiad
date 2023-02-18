package me.kofesst.vkservices.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.kofesst.vkservices.data.remote.ServicesApi
import me.kofesst.vkservices.data.repositories.ServicesRepositoryImpl
import me.kofesst.vkservices.domain.repositories.ServicesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {
    @Provides
    @Singleton
    fun provideServicesRepository(
        servicesApi: ServicesApi,
    ): ServicesRepository {
        return ServicesRepositoryImpl(servicesApi)
    }
}