package com.hivecode.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hivecode.data.mapper.InfoMapper
import com.hivecode.data.model.Card
import com.hivecode.data.model.Info
import com.hivecode.data.service.InfoService
import io.reactivex.disposables.Disposable

class InfoRepository(
    private val infoService: InfoService
) {

    private val _infoResult = MutableLiveData<Info>()
    val infoResult: LiveData<List<Card>>
        get() = infoResult

    private val _errorResult = MutableLiveData<Throwable>()
    val errorResult: LiveData<Throwable>
        get() = errorResult

    fun fetchInfo(): Disposable =
        infoService
            .fetchInfo()
            .map {
                infoResponse -> InfoMapper().from(infoResponse)
             }
            .subscribe(
                { _infoResult.value = it},
                { _errorResult.value = it}
            )
}