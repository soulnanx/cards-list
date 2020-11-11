package com.hivecode.hearthstonecards.di

import com.hivecode.data.repository.CardRepository
import com.hivecode.data.repository.CardTypeRepository
import com.hivecode.data.service.CardService
import com.hivecode.data.service.CardTypeService
import com.hivecode.hearthstonecards.ui.cardList.viewModel.CardListViewModel
import com.hivecode.hearthstonecards.ui.cardType.viewModel.CardTypeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CardTypeViewModel( get() ) }
    viewModel { CardListViewModel( get() ) }
}

val repositoryModule = module {
    single { CardTypeRepository( get() ) }
    single { CardRepository( get() ) }
    single { CardService() }
    single { CardTypeService() }
}