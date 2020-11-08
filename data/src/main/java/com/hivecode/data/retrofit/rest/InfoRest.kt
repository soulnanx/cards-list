package com.hivecode.data.retrofit.rest

import com.hivecode.data.service.response.InfoResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface InfoRest {

    @GET("info")
    fun fetchInfo() : Observable<InfoResponse>
}