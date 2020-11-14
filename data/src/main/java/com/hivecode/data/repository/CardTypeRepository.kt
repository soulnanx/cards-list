package com.hivecode.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hivecode.data.mapper.CardTypeInfoMapper
import com.hivecode.data.model.CardTypeInfo
import com.hivecode.data.service.CardTypeService
import io.reactivex.disposables.Disposable

class CardTypeRepository(
    private val cardTypeService: CardTypeService
) {
    private val _cardTypeResult = MutableLiveData<List<CardTypeInfo>>()
    val cardTypeInfoResult: LiveData<List<CardTypeInfo>>
        get() = _cardTypeResult

    private val _errorResult = MutableLiveData<Throwable>()
    val errorResult: LiveData<Throwable>
        get() = _errorResult

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun fetchCardType(): Disposable =
        cardTypeService
            .fetchCardType()
            .doOnSubscribe { _loading.value = true }
            .map {
                infoResponse -> CardTypeInfoMapper().from(infoResponse)
             }
            .doOnTerminate { _loading.value = false }
            .subscribe(
                { _cardTypeResult.value = it},
                { _errorResult.value = it}
            )
}