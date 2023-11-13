package com.example.paginationchallenge.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.paginationchallenge.data.remote.MarvelApiClient
import com.example.paginationchallenge.domain.MarvelCharacter
import com.example.paginationchallenge.domain.MarvelCharactersRepository
import kotlinx.coroutines.flow.Flow

class MarvelCharactersRepositoryImpl(private val apiClient: MarvelApiClient) :
    MarvelCharactersRepository {

    private val characters: Flow<PagingData<MarvelCharacter>> = Pager(PagingConfig(pageSize = 20)) {
        MarvelCharactersDataSources(marvelApiClient = apiClient)
    }.flow

    override fun getCharacters(): Flow<PagingData<MarvelCharacter>> = characters
}