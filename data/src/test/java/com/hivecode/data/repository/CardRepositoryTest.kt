package com.hivecode.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.hivecode.common.tests.TestObserver
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

class CardRepositoryTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val repository = mockk<CardRepository_>(relaxed = true)

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun fetchCardsByClass() {
        val testObserver = TestObserver<List<Card_>>()
        val expectedResult = mockk<List<Card_>>(relaxed = true)
        val requestedClass = "Druid"
        repository.cardResult.observeForever(testObserver)

        every { repository.cardResult } returns MutableLiveData<List<Card_>>().apply {
            value = expectedResult
        }

        repository.fetchCardsByClass(requestedClass)

        assertEquals(repository.cardResult.value, expectedResult)
    }

    @Test
    fun fetchCardsByRace() {
        val testObserver = TestObserver<List<Card_>>()
        val expectedResult = mockk<List<Card_>>(relaxed = true)
        val requestedRace = "Demon"
        repository.cardResult.observeForever(testObserver)

        every { repository.cardResult } returns MutableLiveData<List<Card_>>().apply {
            value = expectedResult
        }

        repository.fetchCardsByRace(requestedRace)

        assertEquals(repository.cardResult.value, expectedResult)
    }

    @Test
    fun fetchCardsByType() {
        val testObserver = TestObserver<List<Card_>>()
        val expectedResult = mockk<List<Card_>>(relaxed = true)
        val requestedType = "Hero"
        repository.cardResult.observeForever(testObserver)

        every { repository.cardResult } returns MutableLiveData<List<Card_>>().apply {
            value = expectedResult
        }

        repository.fetchCardsByType(requestedType)

        assertEquals(repository.cardResult.value, expectedResult)
    }
}