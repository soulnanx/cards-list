package com.hivecode.data.mapper

import com.hivecode.common.mapper.DataMapper
import com.hivecode.domain.model.CardTypeInfo
import com.hivecode.data.model.ClassCardType_
import com.hivecode.data.model.RaceCardType_
import com.hivecode.data.model.TypeCardType_
import com.hivecode.data.service.response.CardTypeResponse

class CardTypeInfoMapper: DataMapper<CardTypeResponse, List<CardTypeInfo>> {
    override fun from(it: CardTypeResponse) =
        convertCardTypeToList(it)

    private fun convertCardTypeToList(
        response: CardTypeResponse
    ): List<CardTypeInfo> {

        return listOf(
            ClassCardType_(response.classes ?: emptyList()),
            RaceCardType_(response.races ?: emptyList()),
            TypeCardType_(response.types ?: emptyList())
        )
    }
}