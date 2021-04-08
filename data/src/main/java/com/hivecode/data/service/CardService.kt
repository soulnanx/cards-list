package com.hivecode.data.service

import com.hivecode.data.BuildConfig
import com.hivecode.data.retrofit.rest.CardRest
import com.hivecode.data.service.config.ApiConnection
import com.hivecode.data.service.response.CardResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CardService {

    private val api = ApiConnection().create(BuildConfig.REST_ENDPOINT, CardRest::class.java)

    internal fun fetchCardsByClass(playerClass: String) = api.fetchCardsByPlayerClass(playerClass)

    internal fun fetchCardsByRace(race: String) = api.fetchCardsByRace(race)

    internal fun fetchCardsByType(type: String) = api.fetchCardsByType(type)
}