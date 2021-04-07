package com.hivecode.hearthstonecards.ui.cardType.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.hivecode.domain.model.CardTypeInfo
import com.hivecode.data.service.CardTypeService
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CardTypeViewModelTest{

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var errorResultObserver : Observer<Throwable>

    @Mock
    lateinit var resultObserver : Observer<List<CardTypeInfo>>

    private lateinit var viewModel: CardTypeViewModel

    private val expectedSuccess = listOf<CardTypeInfo>(
        CardTypeInfo("title", emptyList())
    )

    private val expectedFailure = Throwable("erro interno")

    @Test
    fun `On init, fetch card type and some error returns `() {
        viewModel = CardTypeViewModel(MockRepository(expectedFailure))
        viewModel.errorResult.observeForever(errorResultObserver)

        verify(errorResultObserver).onChanged(expectedFailure)
    }

    @Test
    fun `On init, fetch card type with success result `() {
        viewModel = CardTypeViewModel(MockRepository(expectedSuccess))
        viewModel.cardTypeInfoResult.observeForever(resultObserver)

        verify(resultObserver).onChanged(expectedSuccess)
    }
}

private class MockRepository(val result: Any) : CardTypeRepository_(CardTypeService()){

    override fun fetchCardType() =
        postResult(result)

    private fun postResult(result: Any): Disposable {
        when (result) {
            is Throwable -> setError(result)
            is List<*> -> setResult(result as List<CardTypeInfo>)
        }
        return Single.just(
            CardTypeInfo(
                String(),
                emptyList()
            )
        ).subscribe()
    }

}