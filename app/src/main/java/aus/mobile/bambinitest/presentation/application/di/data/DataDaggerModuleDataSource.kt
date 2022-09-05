package aus.mobile.bambinitest.presentation.application.di.data

import android.content.Context
import aus.mobile.bambinitest.BuildConfig
import aus.mobile.bambinitest.data.datasource.database.dao.ProductEntityDao
import aus.mobile.bambinitest.data.datasource.database.dao.PromotionEntityDao
import aus.mobile.bambinitest.data.datasource.database.persistent.AppDatabase
import aus.mobile.bambinitest.data.datasource.rest.interceptor.NetworkConnectionInterceptor
import aus.mobile.bambinitest.data.datasource.rest.interceptor.logging.HttpLoggingInterceptor
import aus.mobile.bambinitest.data.datasource.rest.retrofit.adapter.FlowCallAdapterFactory
import aus.mobile.bambinitest.data.datasource.rest.retrofit.converter.UnitConverterFactory
import aus.mobile.bambinitest.data.datasource.rest.service.ProductService
import aus.mobile.bambinitest.data.datasource.rest.service.PromotionService
import aus.mobile.bambinitest.data.utils.json.actual
import aus.mobile.bambinitest.presentation.support.connectivity.Connectivity
import aus.mobile.bambinitest.presentation.support.connectivity.ConnectivityImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
internal object DataDaggerModuleDataSource {

    @JvmStatic
    @Provides
    @Singleton
    fun appDatabase(
        context: Context
    ): AppDatabase =
        AppDatabase.create(context)

    @JvmStatic
    @Provides
    @Singleton
    fun productEntityDao(
        appDatabase: AppDatabase
    ): ProductEntityDao =
        appDatabase.productEntityDao

    @JvmStatic
    @Provides
    @Singleton
    fun promotionEntityDao(
        appDatabase: AppDatabase
    ): PromotionEntityDao =
        appDatabase.promotionEntityDao

    @JvmStatic
    @Provides
    @Singleton
    fun provideConnectivity(
        context: Context
    ): Connectivity =
        ConnectivityImpl(context)

    @JvmStatic
    @Provides
    @Singleton
    fun OkHttpClient(
        connectivity: Connectivity
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(BuildConfig.LOG_ENABLED))
            .addInterceptor(NetworkConnectionInterceptor(connectivity))
            .retryOnConnectionFailure(false)
            .followRedirects(false)
            .followSslRedirects(false)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @JvmStatic
    @Provides
    @Singleton
    fun Retrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(FlowCallAdapterFactory)
            .addConverterFactory(Json.Default.actual.asConverterFactory("application/json; charset=utf-8".toMediaType()))
            .addConverterFactory(UnitConverterFactory)
            .build()

    @JvmStatic
    @Provides
    @Singleton
    fun ProductRestService(
        retrofit: Retrofit
    ): ProductService =
        retrofit.create()

    @JvmStatic
    @Provides
    @Singleton
    fun PromotionService(
        retrofit: Retrofit
    ): PromotionService =
        retrofit.create()

}