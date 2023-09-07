package com.sadapay.assignment.presentation.home.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sadapay.assignment.data.remote.ApiResult
import com.sadapay.assignment.domain.model.search.GitHubRepo
import com.sadapay.assignment.domain.model.search.GithubRepoRequestBody
import com.sadapay.assignment.domain.repository.MainRepository

class TrendingGithubDataSource(
    var query: GithubRepoRequestBody, private val repo: MainRepository
) : PagingSource<Int, GitHubRepo>() {
    private var currentTotal = 0
    private var hasMore: Boolean? = null

    override fun getRefreshKey(state: PagingState<Int, GitHubRepo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitHubRepo> {

        val page = params.key ?: 1
        query = query.copy(page = page, size = 10)
        return when (val result = repo.getTrendingListing(query.query, query.sortBy)) {
            is ApiResult.Success -> {
                val list = result.data ?: arrayListOf()
                currentTotal += list.size
                hasMore = result.totalCount != currentTotal && list.isNotEmpty()
                LoadResult.Page(
                    data = list,
                    prevKey = null,
                    nextKey = if (hasMore == true) page.plus(1) else null
                )
            }
            is ApiResult.Error -> {
                Log.i("HomeScreen", result.error.localizedMessage)

                LoadResult.Error(result.error)
            }


        }

    }
}