package com.example.paginationchallenge.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.URLBuilder
import io.ktor.http.Url

internal class MarvelApiClientImpl(private val client: HttpClient) : MarvelApiClient {

    override suspend fun getCharacters(offset: Long, limit: Long): ApiResponse {
        val urlBuilder =
            URLBuilder(urlString = "https://gateway.marvel.com/v1/public/characters?offset=$offset&limit=$limit")
        return client.get(url = Url(urlBuilder))
    }

    override suspend fun findCharacter(id: Long): Character {
        val urlBuilder =
            URLBuilder(urlString = "https://gateway.marvel.com/v1/public/characters/$id")
        return client.get<ApiResponse>(url = Url(urlBuilder)).data.results.first()
    }
}
