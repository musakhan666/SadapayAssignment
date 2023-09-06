package com.sadapay.assignment.domain.repository

import com.sadapay.assignment.data.remote.ApiResult
import com.sadapay.assignment.domain.model.search.GitHubRepo

interface MainRepository {

    // function to fetch trending github profiles
    suspend fun getTrendingListing(
        query: String,
        sortBy: String
    ): ApiResult<GitHubRepo>

}