package com.sadapay.assignment.data.repository

import android.util.Log
import com.sadapay.assignment.data.local.AppDatabase
import com.sadapay.assignment.data.mapper.toCreateTrendingGithubModels
import com.sadapay.assignment.data.mapper.toDomainModel
import com.sadapay.assignment.data.remote.*
import com.sadapay.assignment.data.remote.dto.base.BaseResponse
import com.sadapay.assignment.data.remote.dto.search.GitHubRepoDTO
import com.sadapay.assignment.data.remote.dto.search.SearchResponse
import com.sadapay.assignment.domain.model.search.GitHubRepo
import com.sadapay.assignment.domain.repository.MainRepository
import com.sadapay.assignment.utils.ResourceProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(
    private val remoteApi: RemoteApi,
    private val database: AppDatabase,
    private val resourceProvider: ResourceProvider,
) : MainRepository {
    override suspend fun getTrendingListing(
        query: String, sortBy: String
    ): ApiResult<GitHubRepo> {
        val result: ApiResult<GitHubRepoDTO> = safeApiBaseResult {
            remoteApi.searchRepositories(query, sortBy)
        }
        //Map the DTO to the domain model to maintain clean architecture
        val mappedResult: ApiResult<GitHubRepo> = when (result) {
            is ApiResult.Error -> ApiResult.Error(result.error)
            is ApiResult.Success -> {
                val mappedData = result.data?.toCreateTrendingGithubModels()
                ApiResult.Success(mappedData, totalCount = result.totalCount)
            }
        }


        return mappedResult
    }


}