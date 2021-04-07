package com.hivecode.data.service

import com.hivecode.data.BuildConfig
import com.hivecode.data.retrofit.rest.CardRest
import com.hivecode.data.service.config.ApiConnection
import com.hivecode.data.service.response.CardResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CardService() {

    private val api = ApiConnection().create(BuildConfig.REST_ENDPOINT, CardRest::class.java)

    internal fun fetchCardsByClass(playerClass: String): Single<List<CardResponse>> {
        return api
            .fetchCardsByPlayerClass(playerClass)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    internal fun fetchCardsByRace(race: String): Single<List<CardResponse>> {
        return api
            .fetchCardsByRace(race)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    internal fun fetchCardsByType(type: String): Single<List<CardResponse>> {
        return api
            .fetchCardsByType(type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}