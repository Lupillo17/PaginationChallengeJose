package com.example.paginationchallenge.core.di

import com.example.paginationchallenge.domain.repository.MarvelApiClient
import com.example.paginationchallenge.data.repository.MarvelCharactersRepositoryImpl
import com.example.paginationchallenge.domain.GetMarvelCharacters
import com.example.paginationchallenge.domain.repository.MarvelCharactersRepository
import com.example.paginationchallenge.ui.mainscreen.CharacterListPresenter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val homeDomainModules = module {
    factory { GetMarvelCharacters(repository = get()) }
}

 val homeInfrastructureModules = module {
    single<MarvelCharactersRepository> { MarvelCharactersRepositoryImpl(apiClient = get<MarvelApiClient>()) }
}

val mainScreenModules = listOf(homeDomainModules, homeInfrastructureModules)