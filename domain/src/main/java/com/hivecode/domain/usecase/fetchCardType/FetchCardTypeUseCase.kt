package com.hivecode.domain.usecase.fetchCardType

import com.hivecode.domain.model.CardTypeInfo
import io.reactivex.Single

interface FetchCardTypeUseCase {

    fun invoke(): Single<List<CardTypeInfo>>
}