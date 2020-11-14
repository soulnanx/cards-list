package com.hivecode.data.mapper

import com.hivecode.data.service.response.CardTypeResponse
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class CardTypeInfoMapperTest {
    private val mapper = CardTypeInfoMapper()

    @Test
    fun `Assert card type mapper`() {
        val classesList = listOf("Driud", "Hunter")
        val racesList = listOf("Orc", "Demon")
        val typesList = listOf("Hero", "Spell")

        val response = mockk<CardTypeResponse> {
            every { this@mockk.classes } returns classesList
            every { this@mockk.races } returns racesList
            every { this@mockk.types } returns typesList
        }

        val cardTypeInfoList = mapper.from(response)

        assertEquals(response.classes!!, cardTypeInfoList[0].types)
        assertEquals(response.races!!, cardTypeInfoList[1].types)
        assertEquals(response.types!!, cardTypeInfoList[2].types)
    }
}