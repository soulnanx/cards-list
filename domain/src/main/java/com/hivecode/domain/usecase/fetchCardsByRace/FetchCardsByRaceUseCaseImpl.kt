package com.hivecode.domain.usecase.fetchCardsByRace

import com.hivecode.domain.model.Card
import com.hivecode.domain.repository.CardRepository
import io.reactivex.Single

class FetchCardsByRaceUseCaseImpl(val repository: CardRepository):
    FetchCardsByRaceUseCase {
    override fun invoke(cardRace: String): Single<List<Card>> {
        return repository.fetchCardsByRace(cardRace)
    }
}
