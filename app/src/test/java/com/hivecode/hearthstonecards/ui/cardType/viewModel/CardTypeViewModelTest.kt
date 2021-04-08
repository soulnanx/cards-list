package com.hivecode.hearthstonecards.ui.cardType.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.hivecode.domain.model.*
import com.hivecode.domain.usecase.fetchCardType.FetchCardTypeUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CardTypeViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val fetchCardTypeUseCase = mockk<FetchCardTypeUseCase>()

    private val success = mockk<Observer<List<CardTypeInfo>>>(relaxed = true)
    private val failure = mockk<Observer<Throwable>>(relaxed = true)

    private val mockedError = Throwable("error")
    private val mockedCardType = CardTypeInfo("any", listOf("any", "any", "any"))
    private val mockedResult = listOf(mockedCardType)

    private lateinit var viewModel: CardTypeViewModel

    private fun instantiateViewModel(): CardTypeViewModel {
        val viewModel = CardTypeViewModel(
            fetchCardTypeUseCase
        )
        viewModel.success.observeForever(success)
        viewModel.failure.observeForever(failure)
        return viewModel
    }

    private fun setUp() {
        this.viewModel = instantiateViewModel()
    }


    @Test
    fun `when try to fetch card by type and some error returns`() {
        every { fetchCardTypeUseCase.invoke() } returns Single.error(mockedError)
        setUp()

        viewModel.performFetchCardTypeUseCase()

        verify { failure.onChanged(mockedError) }
    }

    @Test
    fun `when fetch card by type and its results success`() {
        val result: Single<List<CardTypeInfo>> = Single.just(mockedResult)
        every { fetchCardTypeUseCase.invoke() } returns result
        setUp()

        viewModel.performFetchCardTypeUseCase()

        verify { fetchCardTypeUseCase.invoke() }
        verify { success.onChanged(mockedResult) }
    }

}