package com.example.imagesearchpaging.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.imagesearchpaging.api.UnsplashApi
import com.example.imagesearchpaging.model.UnsplashPhoto
import retrofit2.HttpException
import java.io.IOException

class UnsplashPagingSource(
    private val unsplashApi: UnsplashApi,
    private val query: String
) : PagingSource<Int, UnsplashPhoto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        return try {
            val pageNumber = params.key ?: 0
            val response = unsplashApi.searchPhotos(query, pageNumber, params.loadSize)
            val prevKey = if (pageNumber > 0) pageNumber - 1 else null
            val nextKey = if (response.results.isNotEmpty()) pageNumber + 1 else null
            LoadResult.Page(
                data = response.results,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashPhoto>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}