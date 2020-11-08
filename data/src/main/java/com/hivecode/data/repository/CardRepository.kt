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
        get() = cardResult

    private val _errorResult = MutableLiveData<Throwable>()
    val errorResult: LiveData<Throwable>
        get() = errorResult

    fun fetchCardsByClass(cardClass: String): Disposable =
        cardService
            .fetchCardsByClass(cardClass)
            .map {
                it.map { cardResponse -> CardMapper().from(cardResponse) }
             }
            .subscribe(
                { _cardResult.value = it},
                { _errorResult.value = it}
            )

    fun fetchCardsByRace(playerClass: String): Disposable =
        cardService
            .fetchCardsByRace(playerClass)
            .map {
                it.map { cardResponse -> CardMapper().from(cardResponse) }
            }
            .subscribe(
                { _cardResult.value = it},
                { _errorResult.value = it}
            )

    fun fetchCardsByType(type: String): Disposable =
        cardService
            .fetchCardsByType(type)
            .map {
                it.map { cardResponse -> CardMapper().from(cardResponse) }
            }
            .subscribe(
                { _cardResult.value = it},
                { _errorResult.value = it}
            )
}