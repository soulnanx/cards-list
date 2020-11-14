package com.hivecode.hearthstonecards.ui.cardList.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hivecode.data.model.*
import com.hivecode.data.repository.CardRepository

class CardListViewModel(
    private val repository: CardRepository
) : ViewModel() {

    val cardResult: LiveData<List<Card>>
        get() = repository.cardResult

    val errorResult: LiveData<Throwable>
        get() = repository.errorResult

    val loading: LiveData<Boolean>
        get() = repository.loading

    fun fetchCardByCardTypeInfo(cardTypeInfo: CardTypeInfo, selectedType: String){
        when(cardTypeInfo){
            is ClassCardType -> repository.fetchCardsByClass(selectedType)
            is RaceCardType -> repository.fetchCardsByRace(selectedType)
            is TypeCardType -> repository.fetchCardsByType(selectedType)
        }
    }

}