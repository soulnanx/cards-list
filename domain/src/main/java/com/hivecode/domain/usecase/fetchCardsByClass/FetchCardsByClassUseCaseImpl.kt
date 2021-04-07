package com.hivecode.domain.usecase.fetchCardsByClass

import com.hivecode.domain.model.Card
import com.hivecode.domain.repository.CardRepository
import io.reactivex.Single

class FetchCardsByClassUseCaseImpl(val repository: CardRepository):
    FetchCardsByClassUseCase {
    override fun invoke(cardClass: String): Single<List<Card>> {
        return repository.fetchCardsByClass(cardClass)
    }
}
