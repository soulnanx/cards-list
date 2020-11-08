package com.hivecode.data.retrofit.interceptor

import com.hivecode.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor{

    companion object {
        val X_RAPIDAPI_HOST_KEY = "x-rapidapi-host"
        val X_RAPIDAPI_HOST_VALUE = BuildConfig.REST_HEADER_API_HOST

        val X_RAPIDAPI_KEY_KEY = "x-rapidapi-key"
        val X_RAPIDAPI_KEY_VALUE = BuildConfig.REST_HEADER_API_KEY
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
            .addHeader(X_RAPIDAPI_HOST_KEY, X_RAPIDAPI_HOST_VALUE)
            .addHeader(X_RAPIDAPI_KEY_KEY, X_RAPIDAPI_KEY_VALUE)

        return chain.proceed(builder.build())
    }



}