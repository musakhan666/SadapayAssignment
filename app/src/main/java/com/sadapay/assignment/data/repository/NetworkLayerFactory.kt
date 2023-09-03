package com.sadapay.assignment.data.repository

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.GsonBuilder
import com.sadapay.assignment.data.remote.dto.base.DateTimeTypeAdapter
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.joda.time.DateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object NetworkLayerFactory {
    private const val TIME_OUTS: Long = 120L

    fun makeHttpClient(context: Context): OkHttpClient {
        val logger = HttpLoggingInterceptor()
      return  makeHttpClientBuilder()
            .addInterceptor(logger)
            .addInterceptor(ChuckerInterceptor(context))
            .build()
    }



    fun makeHttpClientBuilder(): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .connectionSpecs(listOf(ConnectionSpec.COMPATIBLE_TLS))
            .connectTimeout(TIME_OUTS, TimeUnit.SECONDS)
            .readTimeout(TIME_OUTS, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUTS, TimeUnit.SECONDS)

    fun createGsonInstance() = GsonBuilder()
        .registerTypeAdapter(DateTime::class.java, DateTimeTypeAdapter)
        .create()

    inline fun <reified T> makeServiceFactory(retrofit: Retrofit): T = retrofit.create(T::class.java)

    fun makeRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(createGsonInstance()))
        .build()




    /**
     * Use the Retrofit builder to build a retrofit object.
     */


}