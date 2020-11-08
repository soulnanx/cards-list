package com.hivecode.data.mapper

import com.hivecode.common.mapper.DataMapper
import com.hivecode.data.model.Card
import com.hivecode.data.service.response.CardResponse

class CardMapper: DataMapper<CardResponse, Card> {
    override fun from(it: CardResponse) =
        Card(
            cardId = it.cardId ?: String(),
            img = it.img ?: String(),
            imgGold = it.imgGold ?: String(),
            name = it.name ?: String()
        )
}