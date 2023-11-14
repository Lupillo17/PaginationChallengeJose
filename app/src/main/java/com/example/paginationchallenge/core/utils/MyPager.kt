package com.example.paginationchallenge.core.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.lang.Integer.min

class ListPagingSource<T : Any>(private val list: List<T>) : PagingSource<Int, T>() {
    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, T> {
        val startIndex = params.key ?: 0
        val endIndex = min(startIndex + params.loadSize, list.size)
        val items = list.subList(startIndex, endIndex)

        return PagingSource.LoadResult.Page(
            items,
            prevKey = if (startIndex > 0) startIndex - params.loadSize else null,
            nextKey = if (endIndex < list.size) endIndex else null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? = 0
}