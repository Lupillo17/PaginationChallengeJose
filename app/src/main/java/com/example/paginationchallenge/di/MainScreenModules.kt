package com.example.paginationchallenge.di

import com.example.paginationchallenge.data.remote.MarvelApiClient
import com.example.paginationchallenge.data.MarvelCharactersRepositoryImpl
import com.example.paginationchallenge.domain.GetMarvelCharacters
import com.example.paginationchallenge.domain.MarvelCharactersRepository
import com.example.paginationchallenge.ui.mainscreen.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val homeAppModules = module {
    viewModel { MainScreenViewModel(getCharacters = get()) }
}

private val homeDomainModules = module {
    factory { GetMarvelCharacters(repository = get()) }
}

private val homeInfrastructureModules = module {
    factory<MarvelCharactersRepository> { MarvelCharactersRepositoryImpl(apiClient = get<MarvelApiClient>()) }
}

val mainScreenModules = listOf(homeAppModules, homeDomainModules, homeInfrastructureModules)