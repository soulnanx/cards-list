package com.hivecode.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hivecode.data.mapper.CardMapper
import com.hivecode.data.model.Card
import com.hivecode.data.service.CardService
import io.reactivex.disposables.Disposable

class CardRepository(
    private val cardService: CardService
) {

    private val _cardResult = MutableLiveData<List<Card>>()
    val cardResult: LiveData<List<Card>>
        get() = _cardResult

    private val _errorResult = MutableLiveData<Throwable>()
    val errorResult: LiveData<Throwable>
        get() = _errorResult

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun fetchCardsByClass(cardClass: String): Disposable =
        cardService
            .fetchCardsByClass(cardClass)
            .doOnSubscribe { _loading.value = true }
            .map {
                it.map { cardResponse -> CardMapper().from(cardResponse) }
             }
            .doOnTerminate { _loading.value = false }
            .subscribe(
                { _cardResult.value = it},
                { _errorResult.value = it}
            )

    fun fetchCardsByRace(playerClass: String): Disposable =
        cardService
            .fetchCardsByRace(playerClass)
            .doOnSubscribe { _loading.value = true }
            .map {
                it.map { cardResponse -> CardMapper().from(cardResponse) }
            }
            .doOnTerminate { _loading.value = false }
            .subscribe(
                { _cardResult.value = it},
                { _errorResult.value = it}
            )

    fun fetchCardsByType(type: String): Disposable =
        cardService
            .fetchCardsByType(type)
            .doOnSubscribe { _loading.value = true }
            .map {
                it.map { cardResponse -> CardMapper().from(cardResponse) }
            }
            .doOnTerminate { _loading.value = false }
            .subscribe(
                { _cardResult.value = it},
                { _errorResult.value = it}
            )
}