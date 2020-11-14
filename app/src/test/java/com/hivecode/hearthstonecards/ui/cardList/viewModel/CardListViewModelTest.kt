package com.hivecode.hearthstonecards.ui.cardList.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.hivecode.common.tests.TestObserver
import com.hivecode.data.model.ClassCardType
import com.hivecode.data.repository.CardRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardListViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val repository = mockk<CardRepository>(relaxed = true)
    private val viewModel: CardListViewModel by lazy {
        CardListViewModel(repository)
    }

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when try to fetch card by type and some error returns`() {
        val testObserver = TestObserver<Throwable>()
        val cardType = ClassCardType(emptyList())
        val selectedType = "selected type"
        viewModel.errorResult.observeForever(testObserver)

        every { repository.errorResult } returns MutableLiveData<Throwable>().apply {
            value = Throwable("erro interno")
        }

        viewModel.fetchCardByCardTypeInfo(cardType, selectedType)
        assertEquals(viewModel.errorResult.value?.message, Throwable("erro interno").message)
    }




}