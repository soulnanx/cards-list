package com.hivecode.hearthstonecards.ui.cardType.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hivecode.data.model.CardTypeInfo
import com.hivecode.data.repository.CardTypeRepository

class CardTypeViewModel(
    private val cardTypeRepository: CardTypeRepository
) : ViewModel() {

    init {
        cardTypeRepository.fetchCardType()
    }

    val cardTypeInfoResult: LiveData<List<CardTypeInfo>>
        get() = cardTypeRepository.cardTypeInfoResult

    val errorResult: LiveData<Throwable>
        get() = cardTypeRepository.errorResult

    val loading: LiveData<Boolean>
        get() = cardTypeRepository.loading
}