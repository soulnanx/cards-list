package com.hivecode.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hivecode.common.tests.TestObserver
import com.hivecode.data.model.CardTypeInfo
import com.hivecode.data.model.ClassCardType
import com.hivecode.data.service.response.CardTypeResponse
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

class CardTypeRepositoryTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val repository = mockk<CardTypeRepository>(relaxed = true)

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `fetch cards type`() {
        val testObserver = TestObserver<List<CardTypeInfo>>()
        val expectedResult = mockk<List<CardTypeInfo>>(relaxed = true)
        repository.cardTypeInfoResult.observeForever(testObserver)

        every { repository.cardTypeInfoResult } returns MutableLiveData<List<CardTypeInfo>>().apply {
            value = expectedResult
        }

        repository.fetchCardType()

        assertEquals(repository.cardTypeInfoResult.value, expectedResult)
    }

    @Test
    fun `fetch cards type failure`() {
        val testObserver = TestObserver<Throwable>()
        val expectedResult = mockk<Throwable>(relaxed = true)
        repository.errorResult.observeForever(testObserver)

        every { repository.errorResult } returns MutableLiveData<Throwable>().apply {
            value = expectedResult
        }

        repository.fetchCardType()

        assertEquals(repository.errorResult.value, expectedResult)
    }
}