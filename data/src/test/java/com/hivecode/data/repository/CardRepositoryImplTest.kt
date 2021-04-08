package com.hivecode.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.hivecode.common.tests.TestObserver
import com.hivecode.data.mapper.CardMapper
import com.hivecode.data.mapper.CardTypeInfoMapper
import com.hivecode.data.service.CardService
import com.hivecode.data.service.CardTypeService
import com.hivecode.domain.model.Card
import com.hivecode.domain.repository.CardRepository
import com.nhaarman.mockitokotlin2.any
import io.mockk.*
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

class CardRepositoryImplTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val service = mockk<CardService>(relaxed = true)
    private lateinit var repository: CardRepositoryImpl

    private fun instantiateRepository(): CardRepositoryImpl {
        return CardRepositoryImpl(
            service,
            CardMapper(),
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

    @Before
    fun setUp() {
        repository = instantiateRepository()
    }

    @Test
    fun `fetch cards by class and call its service`() {
        repository.fetchCardsByClass("any").test()

        verify { service.fetchCardsByClass("any") }
    }

    @Test
    fun `fetch cards by type and call its service`() {
        repository.fetchCardsByType("any").test()

        verify { service.fetchCardsByType("any") }
    }

    @Test
    fun `fetch cards by race and call its service`() {
        repository.fetchCardsByRace("any").test()

        verify { service.fetchCardsByRace("any") }
    }


}