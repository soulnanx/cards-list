package com.hivecode.hearthstonecards.ui.cardList.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hivecode.data.model.*
import com.hivecode.data.repository.CardRepository
import io.reactivex.disposables.CompositeDisposable

class CardListViewModel(
    private val repository: CardRepository
) : ViewModel() {

    val disposable = CompositeDisposable()

    val cardResult: LiveData<List<Card>>
        get() = repository.cardResult

    val errorResult: LiveData<Throwable>
        get() = repository.errorResult

    val loading: LiveData<Boolean>
        get() = repository.loading

    fun fetchCardByCardTypeInfo(cardTypeInfo: CardTypeInfo, selectedType: String){
        val dispose = when(cardTypeInfo){
            is RaceCardType -> repository.fetchCardsByRace(selectedType)
            is TypeCardType -> repository.fetchCardsByType(selectedType)
            else -> repository.fetchCardsByClass(selectedType)
        }

        disposable.add(dispose)
    }

}