package com.sadapay.assignment.domain.repository

import com.sadapay.assignment.data.remote.ApiResult
import com.sadapay.assignment.domain.model.search.GitHubRepo

interface MainRepository {
    suspend fun getTrendingListing(
        query: String,
        sortBy: String
    ): ApiResult<List<GitHubRepo>>

}