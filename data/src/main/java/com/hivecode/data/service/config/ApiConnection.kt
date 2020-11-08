package com.hivecode.data.service.config

import com.google.gson.GsonBuilder
import com.grapesnberries.curllogger.CurlLoggerInterceptor
import com.hivecode.data.BuildConfig
import com.hivecode.data.retrofit.interceptor.HeaderInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiConnection {

    fun <S> create(
        host: String,
        serviceClass: Class<S>
    ): S {
        return Retrofit.Builder()
            .baseUrl(host)
            .addConverterFactory(getConverterFactory())
            .client(buildClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(serviceClass)
    }

    private fun getConverterFactory() =
        GsonConverterFactory.create(
            GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create())

    private fun buildClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(CurlLoggerInterceptor())
            .apply {
                if (BuildConfig.DEBUG) this.addInterceptor(HeaderInterceptor())
            }.build()
    }
}