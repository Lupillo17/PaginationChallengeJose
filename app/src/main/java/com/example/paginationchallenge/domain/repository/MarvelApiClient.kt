package com.example.paginationchallenge.domain.repository

import com.example.paginationchallenge.data.remote.ApiResponse
import com.example.paginationchallenge.data.remote.Character

interface MarvelApiClient {
    suspend fun getCharacters(offset: Long, limit: Long): ApiResponse
    suspend fun getCharacters2(offset: Long, limit: Long): ApiResponse?
    suspend fun findCharacter(id: Long): ApiResponse?


}
