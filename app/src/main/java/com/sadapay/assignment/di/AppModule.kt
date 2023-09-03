package com.sadapay.assignment.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.sadapay.assignment.BuildConfig
import com.sadapay.assignment.data.local.AppDatabase
import com.sadapay.assignment.data.remote.RemoteApi
import com.sadapay.assignment.data.repository.NetworkLayerFactory
import com.sadapay.assignment.utils.Constants
import com.sadapay.assignment.utils.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRemoteApi(@ApplicationContext appContext: Context): RemoteApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API_URL)
            .client(
                OkHttpClient.Builder()
                    .connectionSpecs(listOf(ConnectionSpec.COMPATIBLE_TLS,ConnectionSpec.MODERN_TLS,ConnectionSpec.CLEARTEXT))
                    .connectTimeout(120L, TimeUnit.SECONDS)
                    .readTimeout(120L, TimeUnit.SECONDS)
                    .writeTimeout(120L, TimeUnit.SECONDS)
                    .addInterceptor(ChuckerInterceptor.Builder(appContext).build())
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemoteApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            Constants.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(@ApplicationContext appContext: Context): OkHttpClient {
        return NetworkLayerFactory.makeHttpClient(appContext)
    }

    @Provides
    fun provideResourceProvider(@ApplicationContext appContext: Context) =
        ResourceProvider(appContext)



}