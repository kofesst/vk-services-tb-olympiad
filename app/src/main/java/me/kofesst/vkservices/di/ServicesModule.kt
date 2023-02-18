package me.kofesst.vkservices.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.kofesst.vkservices.data.remote.ServicesApi
import me.kofesst.vkservices.presentation.utils.OfflineInterceptor
import me.kofesst.vkservices.presentation.utils.OnlineInterceptor
import me.kofesst.vkservices.presentation.utils.buildOfflineInterceptor
import me.kofesst.vkservices.presentation.utils.buildOnlineInterceptor
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {
    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache {
        val cacheDirectory = File(context.cacheDir, "cached_services")
        val cacheSize: Long = 20 * 1024 * 1024 // 20 MB
        return Cache(cacheDirectory, cacheSize)
    }

    @Provides
    @Singleton
    @OnlineInterceptor
    fun provideOnlineInterceptor(@ApplicationContext context: Context) =
        buildOnlineInterceptor(context)

    @Provides
    @Singleton
    @OfflineInterceptor
    fun provideOfflineInterceptor(@ApplicationContext context: Context) =
        buildOfflineInterceptor(context)

    @Provides
    @Singleton
    fun provideApiServiceClient(
        @OnlineInterceptor onlineInterceptor: Interceptor,
        @OfflineInterceptor offlineInterceptor: Interceptor,
        cache: Cache,
    ): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(offlineInterceptor)
            .addNetworkInterceptor(onlineInterceptor)
            .cache(cache)
            .build()
    }

    @Provides
    @Singleton
    fun provideServicesApi(
        apiServiceClient: OkHttpClient,
    ): ServicesApi {
        return Retrofit.Builder()
            .baseUrl("https://mobile-olympiad-trajectory.hb.bizmrg.com")
            .client(apiServiceClient)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(ServicesApi::class.java)
    }
}