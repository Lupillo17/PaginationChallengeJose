package com.example.paginationchallenge.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface MarvelCharactersRepository {
    fun getCharacters(): Flow<PagingData<MarvelCharacter>>
}