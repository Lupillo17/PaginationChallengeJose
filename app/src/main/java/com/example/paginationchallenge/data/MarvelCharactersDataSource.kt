package com.example.paginationchallenge.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paginationchallenge.data.remote.Character
import com.example.paginationchallenge.data.remote.MarvelApiClient
import com.example.paginationchallenge.data.remote.asString
import com.example.paginationchallenge.domain.MarvelCharacter

class MarvelCharactersDataSources(
    private val marvelApiClient: MarvelApiClient
) : PagingSource<Long, MarvelCharacter>() {

    override fun getRefreshKey(state: PagingState<Long, MarvelCharacter>): Long? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, MarvelCharacter> {
        return try {
            val nextPage = params.key ?: 1
            val response = marvelApiClient.getCharacters(
                offset = nextPage,
                limit = params.loadSize.toLong()
            )

            val count = response.data.count
            val limit = response.data.limit

            LoadResult.Page(
                data = response.data.results.map { it.asCharacter() },
                prevKey = if (nextPage == 1L) null else nextPage - limit,
                nextKey = if (count < limit) null else nextPage + limit,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

private fun Character.asCharacter() = MarvelCharacter(
    id = this@asCharacter.id,
    name = this@asCharacter.name,
    image = this@asCharacter.thumbnail.asString()
)
