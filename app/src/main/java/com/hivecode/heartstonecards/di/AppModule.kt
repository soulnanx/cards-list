package com.hivecode.heartstonecards.di

import com.hivecode.heartstonecards.ui.cardType.viewModel.CardTypeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CardTypeViewModel( ) }
}

val repositoryModule = module {
    single {  }
}