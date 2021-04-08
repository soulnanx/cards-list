package com.hivecode.hearthstonecards.ui.cardList.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.hivecode.domain.model.*
import com.hivecode.domain.usecase.fetchCardsByClass.FetchCardsByClassUseCase
import com.hivecode.domain.usecase.fetchCardsByRace.FetchCardsByRaceUseCase
import com.hivecode.domain.usecase.fetchCardsByType.FetchCardsByTypeUseCase
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
class CardListViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val fetchCardsByClassUseCase = mockk<FetchCardsByClassUseCase>()
    private val fetchCardsByRaceUseCase = mockk<FetchCardsByRaceUseCase>()
    private val fetchCardsByTypeUseCase = mockk<FetchCardsByTypeUseCase>()

    private val success = mockk<Observer<List<Card>>>(relaxed = true)
    private val failure = mockk<Observer<Throwable>>(relaxed = true)

    private val mockedError = Throwable("error")
    private val mockedCard = Card("any", "any", "any", "any")
    private val mockedResult = listOf(mockedCard)

    private lateinit var viewModel: CardListViewModel

    private fun instantiateViewModel(): CardListViewModel {
        val viewModel = CardListViewModel(
            fetchCardsByClassUseCase,
            fetchCardsByRaceUseCase,
            fetchCardsByTypeUseCase
        )
        viewModel.success.observeForever(success)
        viewModel.failure.observeForever(failure)
        return viewModel
    }

    @Before
    fun setUp() {
        this.viewModel = instantiateViewModel()
    }


    @Test
    fun `when try to fetch card by type and some error returns`() {
        every { fetchCardsByTypeUseCase.invoke("any") } returns Single.error(mockedError)

        viewModel.fetchCardByCardTypeInfo(TypeCardType(), "any")

        verify { failure.onChanged(mockedError) }
    }

    @Test
    fun `when fetch card by type and its results success`() {
        val result: Single<List<Card>> = Single.just(mockedResult)
        every { fetchCardsByTypeUseCase.invoke("any") } returns result

        viewModel.fetchCardByCardTypeInfo(TypeCardType(), "any")

        verify { fetchCardsByTypeUseCase.invoke("any") }
        verify { success.onChanged(mockedResult) }
    }

    @Test
    fun `when try to fetch card by class and some error returns`() {
        every { fetchCardsByClassUseCase.invoke("any") } returns Single.error(mockedError)

        viewModel.fetchCardByCardTypeInfo(ClassCardType(), "any")

        verify { failure.onChanged(mockedError) }
    }

    @Test
    fun `when fetch card by class and its results success`() {
        val result: Single<List<Card>> = Single.just(mockedResult)
        every { fetchCardsByClassUseCase.invoke("any") } returns result

        viewModel.fetchCardByCardTypeInfo(ClassCardType(), "any")

        verify { fetchCardsByClassUseCase.invoke("any") }
        verify { success.onChanged(mockedResult) }
    }

    @Test
    fun `when try to fetch card by race and some error returns`() {
        every { fetchCardsByRaceUseCase.invoke("any") } returns Single.error(mockedError)

        viewModel.fetchCardByCardTypeInfo(RaceCardType(), "any")

        verify { failure.onChanged(mockedError) }
    }

    @Test
    fun `when fetch card by race and its results success`() {
        val result: Single<List<Card>> = Single.just(mockedResult)
        every { fetchCardsByRaceUseCase.invoke("any") } returns result

        viewModel.fetchCardByCardTypeInfo(RaceCardType(), "any")

        verify { fetchCardsByRaceUseCase.invoke("any") }
        verify { success.onChanged(mockedResult) }
    }

}