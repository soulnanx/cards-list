package com.hivecode.hearthstonecards.ui.cardType.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.hivecode.common.tests.TestObserver
import com.hivecode.data.model.CardTypeInfo
import com.hivecode.data.repository.CardTypeRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardTypeViewModelTest{

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val repository = mockk<CardTypeRepository>(relaxed = true)
    private val viewModel: CardTypeViewModel by lazy {
        CardTypeViewModel(repository)
    }

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `On init, fetch card type`() {
        val testObserver = TestObserver<List<CardTypeInfo>>()
        val expectedResult = mockk<List<CardTypeInfo>>(relaxed = true)
        viewModel.cardTypeInfoResult.observeForever(testObserver)

        every { repository.cardTypeInfoResult } returns MutableLiveData<List<CardTypeInfo>>().apply {
            value = expectedResult
        }

        assertEquals(viewModel.cardTypeInfoResult.value, expectedResult)
    }
}