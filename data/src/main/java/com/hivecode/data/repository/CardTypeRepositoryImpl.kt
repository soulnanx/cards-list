package com.hivecode.data.repository

import com.hivecode.data.mapper.CardTypeInfoMapper
import com.hivecode.data.service.CardTypeService
import com.hivecode.domain.model.CardTypeInfo
import com.hivecode.domain.repository.CardTypeRepository
import io.reactivex.Single

open class CardTypeRepositoryImpl(
    private val cardTypeService: CardTypeService,
    private val mapper: CardTypeInfoMapper
) : CardTypeRepository {

    override fun fetchCardType(): Single<List<CardTypeInfo>> =
        cardTypeService
            .fetchCardType()
            .map {
                infoResponse -> mapper.from(infoResponse)
             }
}