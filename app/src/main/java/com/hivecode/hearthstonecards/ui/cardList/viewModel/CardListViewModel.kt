package com.hivecode.hearthstonecards.ui.cardList.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hivecode.domain.model.Card
import com.hivecode.domain.model.CardTypeInfo
import com.hivecode.domain.model.RaceCardType
import com.hivecode.domain.model.TypeCardType
import com.hivecode.domain.usecase.fetchCardsByClass.FetchCardsByClassUseCase
import com.hivecode.domain.usecase.fetchCardsByRace.FetchCardsByRaceUseCase
import com.hivecode.domain.usecase.fetchCardsByType.FetchCardsByTypeUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class CardListViewModel(
    private val fetchCardsByClassUseCase: FetchCardsByClassUseCase,
    private val fetchCardsByRaceUseCase: FetchCardsByRaceUseCase,
    private val fetchCardsByTypeUseCase: FetchCardsByTypeUseCase
) : ViewModel() {

    val disposable = CompositeDisposable()

    val success = MutableLiveData<List<Card>>()
    val failure = MutableLiveData<Throwable>()
    val loading = MutableLiveData<Boolean>()

    fun fetchCardByCardTypeInfo(cardTypeInfo: CardTypeInfo, selectedType: String){
        val useCase = when(cardTypeInfo){
            is RaceCardType -> fetchCardsByRaceUseCase.invoke(selectedType)
            is TypeCardType -> fetchCardsByTypeUseCase.invoke(selectedType)
            else -> fetchCardsByClassUseCase.invoke(selectedType)
        }

        val dispose = useCase
            .doOnSubscribe(showLoading())
            .doAfterTerminate(hideLoading())
            .subscribe(
                onSuccess(), onFailure()
            )

        disposable.add(dispose)
    }

    private fun onFailure(): (Throwable) -> Unit {
        return {
            errorHandler(it)
        }
    }

    private fun onSuccess(): (List<Card>) -> Unit {
        return {
            success.value = it
        }
    }

    private fun hideLoading(): () -> Unit {
        return {
            loading.value = false
        }
    }

    private fun showLoading(): (Disposable) -> Unit {
        return {
            loading.value = true
        }
    }

    private fun errorHandler(error: Throwable) {
        failure.value = error
    }

}