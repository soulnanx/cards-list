package com.hivecode.data.retrofit.rest

import com.hivecode.data.service.response.CardResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface CardRest {

    @GET("cards/classes/{class}")
    fun fetchCardsByPlayerClass(
        @Path("class") playerClass: String
    ) : Observable<List<CardResponse>>

    @GET("cards/races/{race}")
    fun fetchCardsByRace(
        @Path("race") race: String
    ) : Observable<List<CardResponse>>

    @GET("cards/types/{type}")
    fun fetchCardsByType(
        @Path("type") type: String
    ) : Observable<List<CardResponse>>

}