package com.hivecode.domain.repository

import com.hivecode.domain.model.Card
import com.hivecode.domain.model.CardTypeInfo
import com.hivecode.domain.model.GitRepo
import com.hivecode.domain.model.Result
import io.reactivex.Single
import io.reactivex.disposables.Disposable

interface CardRepository {

    fun fetchCardsByClass(cardClass: String): Single<List<Card>>
    fun fetchCardsByRace(playerClass: String): Single<List<Card>>
    fun fetchCardsByType(type: String): Single<List<Card>>
}