package com.hivecode.data.mapper

import com.hivecode.common.mapper.DataMapper
import com.hivecode.data.model.CardTypeInfo
import com.hivecode.data.model.ClassCardType
import com.hivecode.data.model.RaceCardType
import com.hivecode.data.model.TypeCardType
import com.hivecode.data.service.response.CardTypeResponse

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