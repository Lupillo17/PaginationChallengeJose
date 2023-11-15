package com.example.paginationchallenge.domain.repository

import androidx.paging.PagingData
import com.example.paginationchallenge.data.remote.ApiResponse
import com.example.paginationchallenge.data.remote.Character
import com.example.paginationchallenge.domain.model.MarvelCharacter
import kotlinx.coroutines.flow.Flow

interface MarvelCharactersRepository {
    fun getCharacters(): Flow<PagingData<MarvelCharacter>>
    suspend fun getCharacters2(): ApiResponse?

    suspend fun findCharacter(): ApiResponse?
}