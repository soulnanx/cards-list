package com.hivecode.domain.usecase.fetchCardType

import com.hivecode.domain.model.CardTypeInfo
import com.hivecode.domain.repository.CardTypeRepository
import io.reactivex.Single

class FetchCardTypeUseCaseImpl(val repository: CardTypeRepository):
    FetchCardTypeUseCase {
    override fun invoke(): Single<List<CardTypeInfo>> {
        return repository.fetchCardType()
    }
}