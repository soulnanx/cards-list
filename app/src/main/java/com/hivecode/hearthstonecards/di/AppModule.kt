package com.hivecode.hearthstonecards.di

import com.hivecode.data.mapper.GitReposMapper
import com.hivecode.data.repository.CardRepository
import com.hivecode.data.repository.CardTypeRepository
import com.hivecode.data.repository.GithubRepositoryImpl
import com.hivecode.data.service.CardService
import com.hivecode.data.service.CardTypeService
import com.hivecode.data.service.GithubService
import com.hivecode.domain.repository.GithubRepository
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
    viewModel { CardListViewModel( get()) }
    viewModel { MenuViewModel() }
    viewModel { GitReposViewModel( get()) }
}

val repositoryModule = module {
    single { CardTypeRepository( get()) }
    single { CardRepository( get()) }


    single { CardService() }
    single { CardTypeService() }
    single { GithubService() }
}

// TODO mover para o modulo correto
val mapperModule = module {
    single { GitReposMapper() }
}

// TODO mover para o modulo correto
val useCaseModule = module {
    factory<FetchGithubUseCase> { FetchGithubUseCaseImpl(get()) }
    factory<GithubRepository> { GithubRepositoryImpl(get(), get()) }
}