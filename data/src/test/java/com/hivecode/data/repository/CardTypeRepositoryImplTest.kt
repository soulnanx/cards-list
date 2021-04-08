package com.hivecode.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hivecode.data.mapper.CardTypeInfoMapper
import com.hivecode.data.service.CardTypeService
import com.hivecode.data.service.response.CardTypeResponse
import com.hivecode.domain.model.CardTypeInfo
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class CardTypeRepositoryImplTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val service = mockk<CardTypeService>(relaxed = true)
    private lateinit var repository: CardTypeRepositoryImpl

    private fun instantiateRepository(): CardTypeRepositoryImpl {
        return CardTypeRepositoryImpl(
            service,
            CardTypeInfoMapper(),
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

    @Before
    fun setUp() {
        repository = instantiateRepository()
    }

    @Test
    fun `fetch cards type`() {
        repository.fetchCardType().test()

        verify { service.fetchCardType() }
    }

}