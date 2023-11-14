package com.example.paginationchallenge.core.di

import com.example.paginationchallenge.domain.repository.MarvelApiClient
import com.example.paginationchallenge.data.repository.MarvelCharactersRepositoryImpl
import com.example.paginationchallenge.domain.GetMarvelCharacters
import com.example.paginationchallenge.domain.repository.MarvelCharactersRepository
import com.example.paginationchallenge.ui.mainscreen.MainScreenPresenter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val homeAppModules = module {
    viewModel { MainScreenPresenter(getCharacters = get(), repository = get()) }
}

private val homeDomainModules = module {
    factory { GetMarvelCharacters(repository = get()) }
}

private val homeInfrastructureModules = module {
    factory<MarvelCharactersRepository> { MarvelCharactersRepositoryImpl(apiClient = get<MarvelApiClient>()) }
}

val mainScreenModules = listOf(homeAppModules, homeDomainModules, homeInfrastructureModules)