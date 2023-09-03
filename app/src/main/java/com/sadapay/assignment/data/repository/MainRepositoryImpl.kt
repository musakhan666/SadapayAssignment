package com.sadapay.assignment.data.repository

import com.sadapay.assignment.data.local.AppDatabase
import com.sadapay.assignment.data.mapper.toCreateTrendingGithubModels
import com.sadapay.assignment.data.remote.ApiResult
import com.sadapay.assignment.data.remote.RemoteApi
import com.sadapay.assignment.data.remote.dto.base.BaseListBody
import com.sadapay.assignment.data.remote.dto.search.GitHubRepoDTO
import com.sadapay.assignment.data.remote.mapDtoToDomain
import com.sadapay.assignment.data.remote.safeApiBaseResult
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
        query: String,
        sortBy: String
    ): ApiResult<List<GitHubRepo>> {
        val result: ApiResult<BaseListBody<GitHubRepoDTO>> = safeApiBaseResult {
            remoteApi.searchRepositories(query, sortBy)
        }
        //Map the DTO to the domain model to maintain clean architecture
        return result.mapDtoToDomain { toCreateTrendingGithubModels() }
    }


}