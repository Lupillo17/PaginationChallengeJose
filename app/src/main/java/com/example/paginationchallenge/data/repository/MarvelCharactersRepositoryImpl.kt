package com.example.paginationchallenge.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.paginationchallenge.data.MarvelCharactersDataSources
import com.example.paginationchallenge.data.remote.ApiResponse
import com.example.paginationchallenge.domain.repository.MarvelApiClient
import com.example.paginationchallenge.domain.model.MarvelCharacter
import com.example.paginationchallenge.domain.repository.MarvelCharactersRepository
import kotlinx.coroutines.flow.Flow

class MarvelCharactersRepositoryImpl(private val apiClient: MarvelApiClient) :
    MarvelCharactersRepository {

    private val characters: Flow<PagingData<MarvelCharacter>> = Pager(PagingConfig(pageSize = 20)) {
        MarvelCharactersDataSources(marvelApiClient = apiClient)
    }.flow

    override fun getCharacters(): Flow<PagingData<MarvelCharacter>> = characters
    override suspend fun getCharacters2(): ApiResponse? {
        return apiClient.getCharacters2(1,10)
    }
}