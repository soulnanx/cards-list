package com.hivecode.hearthstonecards.di

import com.hivecode.hearthstonecards.ui.cardType.viewModel.CardTypeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CardTypeViewModel( ) }
}

val repositoryModule = module {
    single {  }
}