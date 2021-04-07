package com.hivecode.domain.usecase.fetchCardsByType

import com.hivecode.domain.model.Card
import com.hivecode.domain.repository.CardRepository
import io.reactivex.Single

class FetchCardsByTypeUseCaseImpl(val repository: CardRepository):
    FetchCardsByTypeUseCase {
    override fun invoke(type: String): Single<List<Card>> {
        return repository.fetchCardsByType(type)
    }
}
