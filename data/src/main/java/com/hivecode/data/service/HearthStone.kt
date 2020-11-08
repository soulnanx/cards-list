package com.hivecode.data.service

import com.hivecode.data.BuildConfig
import com.hivecode.data.retrofit.rest.HearthStoneRest
import com.hivecode.data.service.config.ApiConnection
import com.hivecode.data.service.response.CardResponse
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class HeartStoneService() {

    private val api = ApiConnection().create(BuildConfig.REST_ENDPOINT, HearthStoneRest::class.java)

    internal fun fetchCardsByClass(playerClass: String): Observable<List<CardResponse>> {
        return api
            .fetchCardsByPlayerClass(playerClass)
            .subscribeOn(Schedulers.io())
    }

    internal fun fetchCardsByRace(race: String): Observable<List<CardResponse>> {
        return api
            .fetchCardsByRace(race)
            .subscribeOn(Schedulers.io())
    }

    internal fun fetchCardsByType(type: String): Observable<List<CardResponse>> {
        return api
            .fetchCardsByType(type)
            .subscribeOn(Schedulers.io())
    }
}