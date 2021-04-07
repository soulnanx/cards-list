package com.hivecode.hearthstonecards.di

import com.hivecode.data.mapper.CardMapper
import com.hivecode.data.mapper.CardTypeInfoMapper
import com.hivecode.data.mapper.GitReposMapper
import com.hivecode.data.repository.*
import com.hivecode.data.service.CardService
import com.hivecode.data.service.CardTypeService
import com.hivecode.data.service.GithubService
import com.hivecode.domain.repository.CardRepository
import com.hivecode.domain.repository.CardTypeRepository
import com.hivecode.domain.repository.GithubRepository
import com.hivecode.domain.usecase.fetchCardType.FetchCardTypeUseCase
import com.hivecode.domain.usecase.fetchCardType.FetchCardTypeUseCaseImpl
import com.hivecode.domain.usecase.fetchCardsByClass.FetchCardsByClassUseCase
import com.hivecode.domain.usecase.fetchCardsByClass.FetchCardsByClassUseCaseImpl
import com.hivecode.domain.usecase.fetchCardsByRace.FetchCardsByRaceUseCase
import com.hivecode.domain.usecase.fetchCardsByRace.FetchCardsByRaceUseCaseImpl
import com.hivecode.domain.usecase.fetchCardsByType.FetchCardsByTypeUseCase
import com.hivecode.domain.usecase.fetchCardsByType.FetchCardsByTypeUseCaseImpl
import com.hivecode.domain.usecase.github.FetchGithubUseCase
import com.hivecode.domain.usecase.github.FetchGithubUseCaseImpl
import com.hivecode.hearthstonecards.ui.cardList.viewModel.CardListViewModel
import com.hivecode.hearthstonecards.ui.cardType.viewModel.CardTypeViewModel
import com.hivecode.hearthstonecards.ui.menu.MenuViewModel
import com.hivecode.hearthstonecards.ui.githubRepos.GitReposViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CardTypeViewModel( get()) }
    viewModel { CardListViewModel(get(), get(), get() ) }
    viewModel { MenuViewModel() }
    viewModel { GitReposViewModel( get()) }
}

val repositoryModule = module {
    single { CardService() }
    single { CardTypeService() }
    single { GithubService() }
}

// TODO mover para o modulo correto
val mapperModule = module {
    single { GitReposMapper() }
    single { CardTypeInfoMapper() }
    single { CardMapper() }
}

// TODO mover para o modulo correto
val useCaseModule = module {
    factory<GithubRepository> { GithubRepositoryImpl(get(), get()) }
    factory<CardRepository> { CardRepositoryImpl(get(), get()) }
    factory<CardTypeRepository> { CardTypeRepositoryImpl(get(), get()) }

    factory<FetchGithubUseCase> { FetchGithubUseCaseImpl(get()) }
    factory<FetchCardTypeUseCase> { FetchCardTypeUseCaseImpl(get()) }
    factory<FetchCardsByTypeUseCase> { FetchCardsByTypeUseCaseImpl(get()) }
    factory<FetchCardsByClassUseCase> { FetchCardsByClassUseCaseImpl(get()) }
    factory<FetchCardsByRaceUseCase> { FetchCardsByRaceUseCaseImpl(get()) }
}