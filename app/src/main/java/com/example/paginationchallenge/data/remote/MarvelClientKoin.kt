package com.example.paginationchallenge.data.remote

import android.content.Context
import com.example.paginationchallenge.data.repository.MarvelApiClientImpl
import com.example.paginationchallenge.domain.repository.MarvelApiClient
import com.example.paginationchallenge.ui.mainscreen.CharacterListPresenter
import org.koin.dsl.module

object MarvelClientKoin {

    val modules = module {
        single <MarvelApiClient> { MarvelApiClientImpl(ktorHttpClient, get<Context>()) }

        single { CharacterListPresenter(get(), get()) }
    }
}
