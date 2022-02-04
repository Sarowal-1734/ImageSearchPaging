package com.example.imagesearchpaging.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.imagesearchpaging.api.UnsplashApi
import com.example.imagesearchpaging.data.UnsplashPagingSource
import com.example.imagesearchpaging.util.Constants.Companion.MAX_PAGE_SIZE
import com.example.imagesearchpaging.util.Constants.Companion.PAGE_SIZE
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRepository @Inject constructor(private val unsplashApi: UnsplashApi) {
    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = MAX_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UnsplashPagingSource(unsplashApi, query) }
        ).liveData
}