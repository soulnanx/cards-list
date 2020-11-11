package com.hivecode.data.service

import com.hivecode.data.BuildConfig
import com.hivecode.data.retrofit.rest.CardTypeRest
import com.hivecode.data.service.config.ApiConnection
import com.hivecode.data.service.response.CardTypeResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CardTypeService() {

    private val api = ApiConnection().create(BuildConfig.REST_ENDPOINT, CardTypeRest::class.java)

    internal fun fetchCardType(): Observable<CardTypeResponse> {
        return api
            .fetchCardType()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}