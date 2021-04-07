package com.hivecode.domain.usecase.fetchCardsByType

import com.hivecode.domain.model.Card
import io.reactivex.Single

interface FetchCardsByTypeUseCase {

    fun invoke(type: String): Single<List<Card>>
}