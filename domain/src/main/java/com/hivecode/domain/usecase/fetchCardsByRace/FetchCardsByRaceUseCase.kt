package com.hivecode.domain.usecase.fetchCardsByRace

import com.hivecode.domain.model.Card
import com.hivecode.domain.model.CardTypeInfo
import io.reactivex.Single

interface FetchCardsByRaceUseCase {

    fun invoke(cardRace: String): Single<List<Card>>
}