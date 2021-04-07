package com.hivecode.hearthstonecards.ui.cardList.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.hivecode.data.model.*
import com.hivecode.data.service.CardService
import com.hivecode.domain.model.CardTypeInfo
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CardListViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var errorResultObserver : Observer<Throwable>

    @Mock
    lateinit var cardResultObserver : Observer<List<Card_>>

    private lateinit var viewModel: CardListViewModel

    private val expectedSuccess = listOf<Card_>(
        Card_("cardId", "img", "imgGold", "name")
    )

    private val expectedFailure = Throwable("erro interno")

    @Test
    fun `when try to fetch card by type and some error returns`() {
        viewModel = CardListViewModel(MockRepository(expectedFailure),,,)
        val cardType = ClassCardType_(emptyList())
        val selectedType = "selected type"

        viewModel.errorResult.observeForever(errorResultObserver)
        viewModel.fetchCardByCardTypeInfo(cardType, selectedType)

        verify(errorResultObserver).onChanged(expectedFailure)
    }

    @Test
    fun `when fetch card by class with success result`() {
        viewModel = CardListViewModel(MockRepository(expectedSuccess),,,)
        val cardType = ClassCardType_(emptyList())
        val selectedType = "selected type"

        viewModel.cardResult.observeForever(cardResultObserver)
        viewModel.fetchCardByCardTypeInfo(cardType, selectedType)

        verify(cardResultObserver).onChanged(expectedSuccess)
    }

    @Test
    fun `when fetch card by race with success result`() {
        viewModel = CardListViewModel(MockRepository(expectedSuccess),,,)
        val cardType = RaceCardType_(emptyList())
        val selectedType = "selected type"

        viewModel.cardResult.observeForever(cardResultObserver)
        viewModel.fetchCardByCardTypeInfo(cardType, selectedType)

        verify(cardResultObserver).onChanged(expectedSuccess)
    }

    @Test
    fun `when fetch card by type with success result`() {
        viewModel = CardListViewModel(MockRepository(expectedSuccess),,,)
        val cardType = TypeCardType_(emptyList())
        val selectedType = "selected type"

        viewModel.cardResult.observeForever(cardResultObserver)
        viewModel.fetchCardByCardTypeInfo(cardType, selectedType)

        verify(cardResultObserver).onChanged(expectedSuccess)
    }
}
private class MockRepository(val result: Any) : CardRepository_(CardService()){

    override fun fetchCardsByClass(cardClass: String) =
        postResult(result)

    override fun fetchCardsByType(type: String) =
        postResult(result)

    override fun fetchCardsByRace(race: String) =
        postResult(result)

    private fun postResult(result: Any): Disposable {
        when (result) {
            is Throwable -> setError(result)
            is List<*> -> setResult(result as List<Card_>)
        }
        return Single.just(
            CardTypeInfo(
                String(),
                emptyList()
            )
        ).subscribe()
    }

}