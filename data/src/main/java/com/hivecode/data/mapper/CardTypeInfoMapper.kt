package com.hivecode.data.mapper

import com.hivecode.common.mapper.DataMapper
import com.hivecode.domain.model.CardTypeInfo
import com.hivecode.data.service.response.CardTypeResponse
import com.hivecode.domain.model.ClassCardType
import com.hivecode.domain.model.RaceCardType
import com.hivecode.domain.model.TypeCardType

class CardTypeInfoMapper: DataMapper<CardTypeResponse, List<CardTypeInfo>> {
    override fun from(it: CardTypeResponse) =
        convertCardTypeToList(it)

    private fun convertCardTypeToList(
        response: CardTypeResponse
    ): List<CardTypeInfo> {

        return listOf(
            ClassCardType(response.classes ?: emptyList()),
            RaceCardType(response.races ?: emptyList()),
            TypeCardType(response.types ?: emptyList())
        )
    }
}