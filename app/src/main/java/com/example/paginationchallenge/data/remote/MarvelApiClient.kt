package com.example.paginationchallenge.data.remote

interface MarvelApiClient {
    suspend fun getCharacters(offset: Long, limit: Long): ApiResponse
    suspend fun findCharacter(id: Long): Character
}
