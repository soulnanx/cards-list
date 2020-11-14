package com.hivecode.data.mapper

import com.hivecode.data.service.response.CardResponse
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class CardMapperTest{

    private val mapper = CardMapper()

    @Test
    fun `Assert card mapper`() {
        val cardId = "cardId"
        val img =  "img"
        val imgGold = "imgGold"
        val name = "name"

        val response = mockk<CardResponse> {
            every { this@mockk.cardId } returns cardId
            every { this@mockk.img } returns img
            every { this@mockk.imgGold } returns imgGold
            every { this@mockk.name } returns name
        }

        val card = mapper.from(response)

        assertEquals(response.cardId, card.cardId)
        assertEquals(response.img, card.img)
        assertEquals(response.imgGold, card.imgGold)
        assertEquals(response.name, card.name)
    }
}