package com.sadapay.assignment.data.remote

import com.sadapay.assignment.data.remote.dto.search.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApi {

    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String
    ): Response<SearchResponse>
}