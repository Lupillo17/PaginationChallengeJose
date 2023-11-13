package com.example.paginationchallenge.data.remote

import org.koin.dsl.module

object MarvelClientKoin {

    val modules = module {
        single<MarvelApiClient> { MarvelApiClientImpl(ktorHttpClient) }
    }
}
