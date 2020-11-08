package com.hivecode.data.service

import com.hivecode.data.BuildConfig
import com.hivecode.data.retrofit.rest.InfoRest
import com.hivecode.data.service.config.ApiConnection
import com.hivecode.data.service.response.InfoResponse
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class InfoService() {

    private val api = ApiConnection().create(BuildConfig.REST_ENDPOINT, InfoRest::class.java)

    internal fun fetchInfo(): Observable<InfoResponse> {
        return api
            .fetchInfo()
            .subscribeOn(Schedulers.io())
    }
}