package com.hivecode.data.retrofit.rest

import com.hivecode.data.service.response.CardResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CardRest {

    @GET("cards/classes/{class}")
    fun fetchCardsByPlayerClass(
        @Path("class") playerClass: String
    ) : Single<List<CardResponse>>

    @GET("cards/races/{race}")
    fun fetchCardsByRace(
        @Path("race") race: String
    ) : Single<List<CardResponse>>

    @GET("cards/types/{type}")
    fun fetchCardsByType(
        @Path("type") type: String
    ) : Single<List<CardResponse>>

}