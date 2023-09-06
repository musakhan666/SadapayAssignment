package com.sadapay.assignment.presentation.home

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sadapay.assignment.domain.model.search.GitHubRepo
import com.sadapay.assignment.domain.model.search.GithubRepoRequestBody
import com.sadapay.assignment.domain.repository.MainRepository
import com.sadapay.assignment.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(val repository: MainRepository) : BaseViewModel() {

    fun getTrendingGithubs(): Flow<PagingData<GitHubRepo>> {
        return Pager(
            PagingConfig(
                pageSize = 24, initialLoadSize = 30, prefetchDistance = 30, maxSize = 84
            )
        ) {
            TrendingGithubDataSource(GithubRepoRequestBody(), repository)
        }.flow.cachedIn(viewModelScope)
    }
}