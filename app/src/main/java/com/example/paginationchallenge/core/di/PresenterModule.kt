package com.example.paginationchallenge.core.di

import androidx.paging.PagingData
import com.example.paginationchallenge.domain.model.MarvelCharacter
import com.example.paginationchallenge.ui.mainscreen.CharacterListContract
import com.example.paginationchallenge.ui.mainscreen.CharacterListPresenter
import kotlinx.coroutines.flow.Flow
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val presenterModule = module {
//    singleOf(::CharacterListPresenter)
    single<CharacterListContract.Presenter> {
        val view = object : CharacterListContract.View {
            override fun showCharacters(characters: Flow<PagingData<MarvelCharacter>>) {
                TODO("Not yet implemented")
            }
        }
        CharacterListPresenter(view, get())

    }
}