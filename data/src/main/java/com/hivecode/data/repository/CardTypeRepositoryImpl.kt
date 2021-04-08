package com.hivecode.data.repository

import com.hivecode.data.mapper.CardTypeInfoMapper
import com.hivecode.data.service.CardTypeService
import com.hivecode.domain.model.CardTypeInfo
import com.hivecode.domain.repository.CardTypeRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class CardTypeRepositoryImpl(
    private val cardTypeService: CardTypeService,
    private val mapper: CardTypeInfoMapper,
    private val ioScheduler: Scheduler,
    private val computationScheduler: Scheduler
) : CardTypeRepository {

    override fun fetchCardType(): Single<List<CardTypeInfo>> =
        cardTypeService
            .fetchCardType()
            .subscribeOn(ioScheduler)
            .observeOn(computationScheduler)
            .map {
                infoResponse -> mapper.from(infoResponse)
             }
}