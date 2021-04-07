package com.hivecode.domain.usecase.fetchCardsByClass

import com.hivecode.domain.model.Card
import io.reactivex.Single

interface FetchCardsByClassUseCase {

    fun invoke(cardClass: String): Single<List<Card>>
}