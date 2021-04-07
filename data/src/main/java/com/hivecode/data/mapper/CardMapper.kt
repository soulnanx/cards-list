package com.hivecode.data.mapper

import com.hivecode.common.mapper.DataMapper
import com.hivecode.data.model.Card_
import com.hivecode.data.service.response.CardResponse
import com.hivecode.domain.model.Card

class CardMapper: DataMapper<List<CardResponse>, List<Card>> {
    override fun from(cardResponseList: List<CardResponse>) =
        cardResponseList.map {
            Card(
                cardId = it.cardId ?: String(),
                img = it.img ?: String(),
                imgGold = it.imgGold ?: String(),
                name = it.name ?: String()
            )
        }

}