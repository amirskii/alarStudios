package com.example.alarstudios.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.alarstudios.data.api.ApiService
import com.example.alarstudios.data.model.place.Place

class PlacePagingSource(
    val apiService: ApiService,
    val code: String
) : PagingSource<Int, Place>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Place> {
        try {
            val nextPageNumber = params.key ?: 1
            val response = apiService.getPlaces(code, nextPageNumber)
            return LoadResult.Page(
                data = response.data,
                prevKey = null, // Only paging forward.
                nextKey = response.page + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Place>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}