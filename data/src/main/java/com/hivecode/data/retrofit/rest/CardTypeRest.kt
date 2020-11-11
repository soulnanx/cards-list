package com.hivecode.data.retrofit.rest

import com.hivecode.data.service.response.CardTypeResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface CardTypeRest {

    @GET("info")
    fun fetchCardType() : Observable<CardTypeResponse>
}