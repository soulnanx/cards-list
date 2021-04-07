package com.hivecode.data.repository

import com.hivecode.data.mapper.CardMapper
import com.hivecode.data.service.CardService
import com.hivecode.domain.model.Card
import com.hivecode.domain.repository.CardRepository
import io.reactivex.Single

open class CardRepositoryImpl(
    private val cardService: CardService,
    private val mapper: CardMapper
) : CardRepository {
    override fun fetchCardsByClass(cardClass: String): Single<List<Card>> =
        cardService
            .fetchCardsByClass(cardClass)
            .map {
                mapper.from(it)
             }

    override fun fetchCardsByRace(playerClass: String): Single<List<Card>> =
        cardService
            .fetchCardsByRace(playerClass)
            .map {
               mapper.from(it)
            }

    override fun fetchCardsByType(type: String) : Single<List<Card>> =
        cardService
            .fetchCardsByType(type)
            .map {
                mapper.from(it)
            }

}