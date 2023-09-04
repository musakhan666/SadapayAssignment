package com.sadapay.assignment.data.mapper

import com.sadapay.assignment.data.remote.dto.base.BaseListBody
import com.sadapay.assignment.data.remote.dto.search.GitHubRepoDTO
import com.sadapay.assignment.domain.model.search.GitHubRepo

// function to convert Dto model to Clean Domain Model
fun BaseListBody<GitHubRepoDTO>.toCreateTrendingGithubModels(): List<GitHubRepo> =
    items?.map { it.toDomainModel() } ?: arrayListOf()

fun GitHubRepoDTO.toDomainModel() = GitHubRepo(
    id = id,
    name = name,
    full_name = full_name,
    score = score,
    description = description,
    owner = owner
)